import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';

@Injectable()
export class TodoService  {

    private API_VERSION = 'api/v1/';

    constructor(private http: HttpClient) { }

    list(){
        return this.http.get('/todo');
    }

    addNew(title: string){
        return this.http.post('/todo?title=' + title, {}, {responseType: 'text'});
    }

    delete(id: number){
        return this.http.delete('/todo/' + id, {responseType: 'text'});
    }

    filter(status: string){
        return this.http.get('/todo/status/' + status);
    }

    completeTask(id: number){
        return this.http.post('/todo/' + id + "/status", {}, {responseType: 'text'});
    }

}
