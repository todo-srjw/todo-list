<template>
  <div class="container">
    <h1 class="title">To-Do List</h1>

    <!-- 입력 필드 -->
    <div class="input-container">
      <input
          type="text"
          placeholder="새로운 작업 추가..."
          class="task-input"
          v-model="newTask"
      />
      <input
          type="date"
          v-model="newStartDate"
          class="date-input"
      />
      <button class="add-btn" @click="addTask">추가</button>
    </div>

    <!-- 할 일 목록 -->
    <ul class="task-list">
      <li v-for="(task, index) in tasks" :key="index" class="task-item">
        <div class="task-row">
          <input
              type="checkbox"
              :checked="task.completed"
              @change="toggleTask(index)"
          />
          <span
              v-if="!task.editing"
              :class="{ completed: task.completed }"
              class="task-text"
          >
            {{ task.text }}
          </span>
          <input
              v-else
              type="text"
              v-model="task.text"
              class="task-edit-input"
          />
          <span class="task-date">시작일: {{ task.startDate || "미정" }}</span>
          <button class="edit-btn" @click="toggleEdit(index)">
            {{ task.editing ? "저장" : "수정" }}
          </button>
          <button class="delete-btn" @click="deleteTask(index)">삭제</button>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import { ref } from "vue";

export default {
  name: "TodoList",
  setup() {
    const newTask = ref(""); // 작업 이름
    const newStartDate = ref(""); // 시작일
    const tasks = ref([]); // 작업 목록

    const addTask = () => {
      if (newTask.value.trim() === "") return; // 작업명이 비어있으면 추가하지 않음
      tasks.value.push({
        text: newTask.value,
        startDate: newStartDate.value || "미정", // 시작일이 없으면 "미정"
        completed: false,
        editing: false,
      });
      newTask.value = ""; // 입력값 초기화
      newStartDate.value = ""; // 시작일 초기화
    };

    const deleteTask = (index) => {
      tasks.value.splice(index, 1); // 작업 삭제
    };

    const toggleTask = (index) => {
      tasks.value[index].completed = !tasks.value[index].completed; // 완료 상태 토글
    };

    const toggleEdit = (index) => {
      tasks.value[index].editing = !tasks.value[index].editing; // 수정 모드 토글
    };

    return {
      newTask,
      newStartDate,
      tasks,
      addTask,
      deleteTask,
      toggleTask,
      toggleEdit,
    };
  },
};
</script>

<style scoped>
/* 전체 화면 스타일 */
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: flex-start; /* 위쪽부터 시작 */
  height: 100vh;
  background-color: #f0f4f8;
}

/* 컨테이너 스타일 */
.container {
  width: 90%;
  max-width: 1200px;
  background: #fff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

/* 제목 스타일 */
.title {
  text-align: center;
  margin-bottom: 20px;
  color: #555;
  font-size: 24px;
  font-weight: bold;
}

/* 입력 필드 스타일 */
.input-container {
  display: flex;
  flex-wrap: wrap; /* 줄 바꿈 허용 */
  gap: 10px;
  margin-bottom: 20px;
}

.task-input {
  flex: 3; /* "새로운 작업 추가" 입력 필드의 너비를 더 넓게 */
  min-width: 250px; /* 최소 너비 확대 */
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ddd;
  border-radius: 5px;
  outline: none;
}

.date-input {
  flex: 1; /* 시작일 입력 필드의 너비 */
  min-width: 150px; /* 최소 너비 설정 */
  padding: 10px;
  font-size: 14px;
  border: 1px solid #ddd;
  border-radius: 5px;
  outline: none;
}

.add-btn {
  padding: 10px 20px;
  background-color: #8a8a8a;
  color: white;
  border: none; /* 테두리 제거 */
  border-radius: 5px;
  cursor: pointer;
  outline: none; /* 포커스 외곽선 제거 */
}

.add-btn:hover {
  background-color: #6f6f6f;
}

/* 할 일 목록 스타일 */
.task-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.task-item {
  border-bottom: 1px solid #ddd;
  padding: 10px 0;
}

.task-row {
  display: flex;
  align-items: center;
  gap: 10px;
  flex-wrap: wrap; /* 화면 크기에 따라 줄 바꿈 */
}

.task-text {
  flex: 2;
  word-wrap: break-word; /* 텍스트 줄바꿈 */
}

.task-text.completed {
  text-decoration: line-through;
  color: #aaa;
}

.task-date {
  flex: 1;
  font-size: 14px;
  color: #555;
}

/* 수정 입력 필드 */
.task-edit-input {
  flex: 3; /* 수정 입력 필드를 넓게 */
  min-width: 250px; /* 최소 너비 확대 */
  padding: 10px;
  font-size: 16px;
  border: 1px solid #ddd;
  border-radius: 5px;
  outline: none;
}

/* 수정 버튼 */
.edit-btn {
  background-color: #8fbc8f;
  color: white;
  border: none; /* 테두리 제거 */
  border-radius: 5px;
  padding: 5px 10px;
  cursor: pointer;
  outline: none; /* 포커스 외곽선 제거 */
}

.edit-btn:hover {
  background-color: #6fa06e;
}

/* 삭제 버튼 */
.delete-btn {
  background-color: #f08080;
  color: white;
  border: none; /* 테두리 제거 */
  border-radius: 5px;
  padding: 5px 10px;
  cursor: pointer;
  outline: none; /* 포커스 외곽선 제거 */
}

.delete-btn:hover {
  background-color: #e07070;
}

/* 반응형 스타일 */
@media (max-width: 768px) {
  .task-row {
    flex-direction: column; /* 작은 화면에서는 세로로 정렬 */
    align-items: flex-start;
  }
}
</style>