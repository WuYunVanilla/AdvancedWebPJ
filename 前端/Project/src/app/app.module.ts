import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CommonModule } from '@angular/common';
import { FileUploadModule } from 'ng2-file-upload';

import { AppComponent } from './app.component';
import { IndexComponent } from './index/index.component';
import { CoursesComponent } from './courses/courses.component';
import { AppRoutingModule } from './app-routing.module';
import { MainComponent } from './main/main.component';
import { MindmapComponent } from './mindmap/mindmap.component';
import { DetailsComponent } from './details/details.component';

import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

import { FormsModule } from '@angular/forms';

import { HttpClientModule } from '@angular/common/http';
import { UserService } from './user.service';
import { CourseService } from './course.service';
import { HomeworkComponent } from './homework/homework.component';
import { MindmapService } from './mindmap.service';
import { NodeService } from './node.service';
import { ModifyPasswordComponent } from './modify-password/modify-password.component';
import { ResourcesComponent } from './resources/resources.component';
import { StuHomeworkComponent } from './stu-homework/stu-homework.component';
import { CoursewareComponent } from './courseware/courseware.component';
import { StuMainComponent } from './stu-main/stu-main.component';
import { StuDetailsComponent } from './stu-details/stu-details.component';
import { StuMindmapComponent } from './stu-mindmap/stu-mindmap.component';
import { StuResourcesComponent } from './stu-resources/stu-resources.component';
import { StuCoursewareComponent } from './stu-courseware/stu-courseware.component';
import { StuCoursesComponent } from './stu-courses/stu-courses.component';
import { RouteguardService } from './routeguard.service';
import { HeaderComponent } from './header/header.component';
import { StuNoteComponent } from './stu-note/stu-note.component';

@NgModule({
    declarations: [
        AppComponent,
        IndexComponent,
        CoursesComponent,
        MainComponent,
        MindmapComponent,
        LoginComponent,
        RegisterComponent,
        DetailsComponent,
        HomeworkComponent,
        ModifyPasswordComponent,
        ResourcesComponent,
        StuHomeworkComponent,
        CoursewareComponent,
        StuMainComponent,
        StuDetailsComponent,
        StuMindmapComponent,
        StuResourcesComponent,
        StuCoursewareComponent,
        StuCoursesComponent,
        HeaderComponent,
        StuNoteComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        NgbModule.forRoot(),
        FormsModule,
        HttpClientModule,
        CommonModule,
        FileUploadModule
    ],
    providers: [
        UserService,
        CourseService,
        MindmapService,
        NodeService,
        RouteguardService
    ],
    bootstrap: [AppComponent],
    entryComponents: [
        LoginComponent,
        RegisterComponent,
        ModifyPasswordComponent
    ]
})
export class AppModule { }
