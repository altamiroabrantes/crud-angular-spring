import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { first, tap, delay } from 'rxjs/operators';
import { Observable } from 'rxjs';

import { Course } from '../model/course';

@Injectable({
  providedIn: 'root',
})
export class CoursesService {
  private readonly API = 'api/courses';

  constructor(private httpClient: HttpClient) {}

  list(): Observable<Course[]> {
    return this.httpClient.get<Course[]>(this.API).pipe(
      first(),
      delay(3000),
      tap((courses) => console.log(courses)),
    );
  }

  loadById(id: string): Observable<Course> {
    return this.httpClient.get<Course>(`${this.API}/${id}`).pipe(first());
  }

   save(record: Partial<Course>): Observable<Course> {
    if (record._id) {
      return this.update(record);
    }
    return this.create(record);
  }

  private create(record: Partial<Course>): Observable<Course> {
    return this.httpClient.post<Course>(this.API, record).pipe(first());
  }

  private update(record: Partial<Course>): Observable<Course> {
    return this.httpClient.put<Course>(`${this.API}/${record._id}`, record).pipe(first());
  }
}
