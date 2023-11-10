import {createRouter, createWebHistory} from "vue-router";
import useWebSettingStore from "../store/webSetting.js";
import pinia from "../store/index.js";
import NProgress from "nprogress";
import {ElMessage} from "element-plus";
import {viewBlog} from "../api/backend/view.js";

const webSettingStore = useWebSettingStore(pinia)
const routes = [
    {
        path: '/',
        name: 'frontend',
        component: () => import("../views/frontend/Index.vue"),
        meta: {
            title: '博客首页',
            requireAuth: false,
        },
        redirect: '/frontend/home',
        children: [
            {
                path: '/frontend/home',
                name: 'home',
                component: () => import("../views/frontend/home/Home.vue"),
                meta: {
                    title : '博客'
                }
            },
            {
                path: '/frontend/archive',
                name: 'archive',
                component: () => import("../views/frontend/archive/Archive.vue"),
                meta: {
                    title: '归档'
                },
            },
            {
                path: '/frontend/moments',
                name: 'moments',
                component:() => import("../views/frontend/moments/Moments.vue"),
                meta: {
                    title: "动态"
                },
            },
            {
                path: `/frontend/category/:id`,
                name: 'category',
                component: () => import("../views/frontend/category/Category.vue"),
                meta: {
                    title: '博客分类'
                }
            },
            {
                path: `/frontend/tag/:id`,
                name: 'tagList',
                component:() => import("../views/frontend/tag/Tag.vue"),
                meta: {
                    title: "博客标签"
                }
            },
            {
                path: `/frontend/blog/:id`,
                name: 'detail',
                component: () => import("../views/frontend/blog/Detail.vue"),
                meta: {
                    title: "博客详情"
                }
            },
            {
                path: '/frontend/about',
                name: 'about',
                component: () => import("../views/frontend/about/AboutMe.vue")
            },

        ]
    },
    {
        path: "/backend",
        redirect: "/backend/home",
        name: 'backend',
        component: () => import("../views/backend/Index.vue"),
        meta: {
            requireAuth: true,
        },
        children: [
            {
            path:'/backend/home',
            name: 'admin',
            component: () => import("../components/backend/index/Main.vue"),
            meta: {
                title: "后台首页",
                requireAuth: true
            },
            },
            {
              path: '/backend/blog/write',
              name: 'WriteBlog',
              component: () => import("../views/backend/blog/Add.vue"),
              meta: {
                  title: "写博客"
              },
            },
            {
                path: '/backend/blog/write/:id',
                name: 'ModifyBlog',
                component: () => import("../views/backend/blog/Add.vue"),
                meta: {
                    title: "修改博客"
                },
            },
            {
                path: '/backend/moment/write',
                name: 'WriteMoment',
                component: () => import("../views/backend/moments/Add.vue"),
                meta: {
                    title: "写动态"
                },
            },
            {
                path: '/backend/moment/write/:id',
                name: 'ModifyMoment',
                component: () => import("../views/backend/moments/Add.vue"),
                meta: {
                    title: "修改动态"
                },
            },
            {
                path: '/backend/blog',
                name: 'blog',
                component: () => import("../views/backend/blog/BlogList.vue"),
                meta: {
                    title: "博客管理"
                },
            },
            {
                path: '/backend/moment',
                name: 'moment',
                component: () => import("../views/backend/moments/MomentList.vue"),
                meta: {
                    title: '动态管理'
                }
            },
            {
                path: "/backend/tag",
                name: 'tag',
                component: () => import("../views/backend/tag/TagList.vue"),
                meta: {
                    title: '标签管理',
                },
            },
            {
                path: '/backend/category',
                name: 'categoryList',
                component: () => import("../views/backend/category/CategoryList.vue"),
                meta: {
                    title: '分类管理'
                }
            },
            {
                path: '/backend/setting',
                name: 'setting',
                component: () => import("../views/backend/webSetting/WebSetting.vue"),
                meta: {
                    title: '网站设置'
                }
            },
            {
                path: '/backend/about',
                name: 'aboutMe',
                component: () => import("../views/backend/about/About.vue"),
                meta: {
                    title: '关于我页面'
                }
            }
        ]
    },
    {
        path: '/login',
        name: 'login',
        component: () => import("../views/Login.vue"),
        meta: {
            title: '登录',
            requireAuth: false,
        }
    },
    {
        path: '/error',
        name: 'error',
        component: () => import("../views/frontend/error/error.vue"),
        meta: {
            title: '错误页'
        }
    }
]
const router = createRouter({
    history: createWebHistory(),
    routes,
})
router.beforeEach(async (to,from,next) =>{
    NProgress.start()
    if(!sessionStorage.getItem('isViewed')){
       await viewBlog()
        sessionStorage.setItem('isViewed',"true")
    }
    if(to.path.includes("/backend") && !localStorage.getItem("sa-token") && to.name !=='login'){
        console.log(to.name)
        ElMessage({
            message: '您还没登录，即将跳转至登录页面',
            type: 'info'
        })
            next({name: 'login'})
    }else {
        next()
    }
})
router.afterEach((to,from) => {
    const title = webSettingStore.suffix? webSettingStore.suffix : 'Thunder的小窝'
    document.title = title + '-' + to.meta.title
    NProgress.done()
})
export default router
