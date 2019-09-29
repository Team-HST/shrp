import Vue from 'vue'
import Router from 'vue-router'
import store from '../store'

// Routes
import paths from './paths'

const requireAuth = () => (to, from, next) => {
  if (to.name === 'Login') {
    return next();
  } else {
    if (store.getters.getUserName !== '') {
      return next();
    } else {
      next('/login');
    }
  }
};
	
function route (path, view, name, props) {
  return {
    name: name || view,
    path,
    props,
    component: (resovle) => import(
      `@/views/${view}.vue`
    ).then(resovle),
    beforeEnter: requireAuth()
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