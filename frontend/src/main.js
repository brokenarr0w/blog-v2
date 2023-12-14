import {createApp} from 'vue'
import App from './App.vue'
import {createPinia} from "pinia";
import router from "./router/index.js";
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import 'nprogress/nprogress.css'
import '@icon-park/vue-next/styles/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
const pinia = createPinia();
app.use(ElementPlus)
app.use(pinia)
app.use(router)
app.config.productionTip = false
console.log(
    '%c Blog %c By Thunder %c https://github.com/brokenarr0w/blog-v2',
    'background:#35495e ; padding: 1px; border-radius: 3px 0 0 3px;  color: #fff',
    'background:#41b883 ; padding: 1px; border-radius: 0 3px 3px 0;  color: #000',
    'background:transparent'
)
app.mount("#app")
