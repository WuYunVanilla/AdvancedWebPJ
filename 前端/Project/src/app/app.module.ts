import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { IndexComponent } from './index/index.component';
import { CoursesComponent } from './courses/courses.component';
import { AppRoutingModule } from './app-routing.module';
import { MainComponent } from './main/main.component';
import { MenuComponent } from './menu/menu.component';
import { MindmapComponent } from './mindmap/mindmap.component';
import { DetailsComponent } from './details/details.component';

@NgModule({
    declarations: [
        AppComponent,
        IndexComponent,
        CoursesComponent,
        MainComponent,
        MenuComponent,
        MindmapComponent,
        DetailsComponent
    ],
    imports: [
        NgbModule.forRoot(),
        BrowserModule,
        AppRoutingModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule { }
