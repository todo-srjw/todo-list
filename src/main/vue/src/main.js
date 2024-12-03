import { createApp } from 'vue'
import App from './App.vue'
import router from './router' // 라우터 가져오기

const app = createApp(App)
app.use(router).mount('#app') //라우터를 앱에 적용하고 마운트