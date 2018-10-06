import {Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {TodoService} from '../service/todo.service';
import {Todo} from '../interface/todo.interface';

@Component({
  selector: 'todo',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent implements OnInit{

  isAdd = false;
  todo: Todo[];
  task: string;
  fiterValue: string;
  constructor(private todoService: TodoService) {

  }

  ngOnInit(){
      this.list();
  }

  list(){
      this.todo = [];
      this.isAdd = false;
      if(!this.fiterValue){
          this.todoService.list().subscribe((data:any) =>{
              this.todo = data;
          });
      } else {
          this.todoService.filter(this.fiterValue).subscribe((data:any) =>{
              this.todo = data;
          });
      }

  }

  addTask(){
    this.todoService.addNew(this.task).subscribe((data: any) =>{
        this.isAdd = false;
        this.task = '';
        this.list();
    })
  }

  delete(id){
      this.todoService.delete(id).subscribe((data:any) => {
            this.list();
      });
  }

  filterTask(status: string){
      if(status === 'ALL'){
          this.fiterValue = '';
          this.list();
      } else {
          this.fiterValue = status;
          this.todo = [];
          this.isAdd = false;
          this.todoService.filter(status).subscribe((data:any) =>{
              this.todo = data;
          });
      }
  }

  completeTask(id: number){
      this.todoService.completeTask(id).subscribe((data: any) =>{
          this.isAdd = false;
          this.task = '';
          this.list();
      })
  }

}
