import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { CoursesComponent } from './courses/courses.component';
import { MainComponent } from './main/main.component';
import {MindmapComponent} from './mindmap/mindmap.component';
import {StuMainComponent} from './stu-main/stu-main.component';

const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'index', component: IndexComponent },
  { path: 'courses', component: CoursesComponent },
  { path: 'main', component: MainComponent },
  { path: 'stu-main', component: StuMainComponent }
]

@NgModule({
  imports: [ RouterModule.forRoot(routes)],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
