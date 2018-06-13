import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { CoursesComponent } from './courses/courses.component';
import { MainComponent } from './main/main.component';
import {MindmapComponent} from './mindmap/mindmap.component';
import {StuMainComponent} from './stu-main/stu-main.component';
import {StuCoursesComponent} from './stu-courses/stu-courses.component';
import {RouteguardService} from './routeguard.service';

const routes: Routes = [
  { path: '', redirectTo: 'index', pathMatch: 'full' },
  { path: 'index', component: IndexComponent },
  { path: 'courses', component: CoursesComponent, canActivate: [RouteguardService] },
  { path: 'stu-courses', component: StuCoursesComponent, canActivate: [RouteguardService] },
  { path: 'main', component: MainComponent, canActivate: [RouteguardService] },
  { path: 'stu-main', component: StuMainComponent, canActivate: [RouteguardService] },
  { path: '**', redirectTo: 'index', pathMatch: 'full' }
]

@NgModule({
  imports: [ RouterModule.forRoot(routes)],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
