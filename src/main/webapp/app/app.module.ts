import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import {RouterModule, Routes} from '@angular/router';
import {TodoListComponent} from './todo/template/todo-list.component';
import {TodoService} from './todo/service/todo.service';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';

const appRoutes: Routes = [

    { path: 'todo',      component: TodoListComponent },
    { path: '', redirectTo: '/todo', pathMatch: 'full'
    }
];

@NgModule({
  declarations: [
    AppComponent,
    TodoListComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [TodoService],
  bootstrap: [AppComponent]
})
export class AppModule { }


