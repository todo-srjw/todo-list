import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '@/views/HomePage.vue';
import BoardList from '@/views/BoardList.vue';

const routes = [
    {
        path: '/',  // 루트 경로
        name: 'Home',  // 이름 지정
        component: HomePage,  // BoardList 컴포넌트로 설정
    },
    {

        path: '/board-list', // 게시판 리스트 경로
        name: 'BoardList',
        component: BoardList,
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