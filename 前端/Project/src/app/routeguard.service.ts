import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';

@Injectable()
export class RouteguardService implements CanActivate {

  constructor(
    private router: Router
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    // 当前路由名称
    const path = route.routeConfig.path;
    // nextRoute: 设置需要路由守卫的路由集合
    const nextRoute = ['courses', 'stu-courses', 'main', 'stu-main'];
    let isLogin;
    if (window.sessionStorage.getItem('isLogin') === 'isLogin') {
      isLogin = true;
    } else {
      isLogin = false;
    }
    // 当前路由是nextRoute指定页时
    if (nextRoute.indexOf(path) >= 0) {
      if (!isLogin) {
        // 未登录，跳转到login
        this.router.navigate(['']);
        return false;
      } else {
        // 已登录，跳转到当前路由
        return true;
      }
    } else {
      return true;
    }
  }
}
