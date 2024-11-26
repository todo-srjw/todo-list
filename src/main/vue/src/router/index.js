import { createRouter, createWebHistory } from 'vue-router';
import Post from '../views/Post.vue';
import Login from '../views/Login.vue';

const routes = [
    {
        path: '/login',
        name: 'Login',
        component: Login,
    },
    {
        path: '/post',
        name: 'Post',
        component: Post,
        meta: { requiresAuth: true },  // 인증된 사용자만 접근 가능
    },
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes,
});

router.beforeEach((to, from, next) => {
    if (to.meta.requiresAuth && !localStorage.getItem('authToken')) {
        // 인증 토큰이 없으면 로그인 페이지로 리디렉션
        next('/login');
    } else {
        next();
    }
});

export default router;