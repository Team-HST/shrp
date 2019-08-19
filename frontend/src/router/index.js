import Vue from 'vue'
import Router from 'vue-router'

// Routes
import paths from './paths'

function route (path, view, name, props) {
  return {
    name: name || view,
    path,
    props,
    component: (resovle) => import(
      `@/views/${view}.vue`
    ).then(resovle)
  }
}

Vue.use(Router)

// Create a new router
const router = new Router({
  mode: 'history',
  routes: paths.map(path => route(path.path, path.view, path.name, path.props)).concat([
    { path: '*', redirect: '/analysis' }
  ])
})

export default router