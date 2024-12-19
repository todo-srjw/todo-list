<template>
  <div class="notification-list">
    <h1>알림</h1>
    <ul>
      <li
          v-for="notification in notifications"
          :key="notification.id"
          :class="{ unread: notification.isRead === 'N'}"
      >
        <div>
          <p>{{ notification.message }}</p>
          <small>{{ formatDate(notification.createdDate) }}</small>
        </div>
        <button
            v-if="notification.isRead === 'N'"
            @click="markAsRead(notification.id)"
        >
          읽음 처리
        </button>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "NotificationList",
  data() {
    return {
      notifications: [],
      // notifications: [
      //   {
      //     id: 1,
      //     message: "새로운 작업이 추가되었습니다.",
      //     isRead: false,
      //     createdAt: "2024-12-06T10:00:00Z",
      //   },
      //   {
      //     id: 2,
      //     message: "작업 마감 기한이 도래했습니다.",
      //     isRead: true,
      //     createdAt: "2024-12-05T08:00:00Z",
      //   },
      // ],
    };
  },
  methods: {
    async fetchNotifications() {
      try {
        const response = await axios.get("/todo/notifications/list");
        this.notifications = response.data;
        console.log(this.notifications);
      } catch (error) {
        console.error("Failed to fetch notifications:", error);
      }
    },
    async markAsRead(notificationId) {
      try {
        await axios.patch(`/todo/notifications/${notificationId}`, {
          isRead: "Y",
        });
        const notification = this.notifications.find(
            (n) => n.id === notificationId
        );
        if (notification) notification.isRead = "Y";
      } catch (error) {
        console.error("Failed to mark notification as read:", error);
      }
    },
    formatDate(dateString) {
      const options = { year: "numeric", month: "short", day: "numeric" };
      return new Date(dateString).toLocaleDateString(undefined, options);
    },
  },
  mounted() {
    this.fetchNotifications();
  },
};
</script>

<style scoped>
.notification-list {
  max-width: 600px;
  margin: 0 auto;
  padding: 1rem;
  border: 1px solid #ccc;
  border-radius: 8px;
  background: #fff;
}
.notification-list h1 {
  text-align: center;
  margin-bottom: 1rem;
}
.notification-list ul {
  list-style: none;
  padding: 0;
}
.notification-list li {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0.5rem;
  margin-bottom: 0.5rem;
  border: 1px solid #ddd;
  border-radius: 4px;
}
.notification-list li.unread {
  background-color: #f0f8ff;
}
.notification-list button {
  background: #007bff;
  color: #fff;
  border: none;
  padding: 0.4rem 0.8rem;
  border-radius: 4px;
  cursor: pointer;
}
.notification-list button:hover {
  background: #0056b3;
}
</style>