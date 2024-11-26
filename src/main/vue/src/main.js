import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'

// Axios 기본 설정
axios.defaults.baseURL = 'http://localhost:8081'; // Spring Boot 서버 URL
axios.defaults.headers.common['Content-Type'] = 'application/json';

createApp(App)
    .provide('axios', axios)
    .use(router)
    .mount('#app')
