import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '@/views/HomePage.vue';
import BoardList from '@/views/board/BoardList.vue';
import BoardWrite from "@/views/board/BoardWrite.vue";
import BoardDetail from "@/views/board/BoardDetail.vue";
import TodoList from "@/views/TodoList.vue";
import NotificationList from "@/views/Notification.vue";
import SignUp from "@/views/member/SignUp.vue";
import SignIn from "@/views/member/SignIn.vue";

const routes = [
    {
        path: '/',
        name: 'Home',
        component: HomePage,
    },
    {
        path: '/board-list',
        name: 'BoardList',
        component: BoardList,
    },
    {
        path: '/board-write',
        name: 'BardWrite',
        component: BoardWrite
    },
    {
        path: '/board-detail/:id',
        name: 'BoardDetail',
        component: BoardDetail
    },
    {
        path: '/sign-up',
        name: 'SignUp',
        component: SignUp
    },
    {
        path: '/sign-in',
        name: 'SignIn',
        component: SignIn
    },
    {
        path: '/todo-list',
        name: 'TodoList',
        component: TodoList
    },
    {
        path: '/notification-list',
        name: 'NotificationList',
        component: NotificationList
    },
];

const router = createRouter({
    history: createWebHistory(), // HTML5 히스토리 모드
    routes,
});

router.beforeEach((to, from) => {
    console.log(`Navigating from ${from.path} to ${to.path}`);
});

export default router;