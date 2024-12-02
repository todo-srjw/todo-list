import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '@/views/HomePage.vue';
import BoardList from '@/views/BoardList.vue';
import BoardWrite from "@/views/BoardWrite.vue";
import BoardDetail from "@/views/BoardDetail.vue";

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
];

const router = createRouter({
    history: createWebHistory(), // HTML5 히스토리 모드
    routes,
});

router.beforeEach((to, from) => {
    console.log(`Navigating from ${from.path} to ${to.path}`);
});

export default router;