import Vue from 'vue'
import Router from 'vue-router'
import store from '../store'

// Routes
import paths from './paths'

const requireAuth = () => (to, from, next) => {
  
  console.log('t:', to);
  console.log('from.name: ', from.name);
  console.log('from.name.true: ', from.name == ' Login');
  next();
  /* if (to.path != '/login' && store.state.accessToken === '') {
    return next();
  } else if (from.name != ' Login' || from.name != null) {
    return next('/login');
  }
  */
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