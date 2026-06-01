import { Component, OnInit } from '@angular/core';
import { FormBuilder, NonNullableFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { CoursesService } from '../../services/courses.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Course } from '../../model/course';

@Component({
  selector: 'app-course-form',
  templateUrl: './course-form.component.html',
  styleUrls: ['./course-form.component.scss']
})
export class CourseFormComponent implements OnInit {

  form = this.formBuilder.group({
      _id: [''],
      name: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(100)]],
      category: ['',[Validators.required]]
    });

  constructor(private formBuilder: NonNullableFormBuilder,
    private service: CoursesService,
    private snackBar: MatSnackBar,
    private location: Location,
    private route: ActivatedRoute
  ) {
   // this.form

   }

  ngOnInit(): void {
    const course: Course = this.route.snapshot.data['course'];
    if(course){
      this.form.setValue({
        _id: course._id,
        name: course.name,
        category: course.category
      });
    }
   }

  onSubmit(){
    this.service.save(this.form.value)
      .subscribe(result => this.onSuccess(), error => this.onError());
  }

  onCancel(){
    this.location.back();
  }

  private onSuccess(){
    this.snackBar.open('Curso salvo com sucesso.','', { duration: 2000});
    this.onCancel();
  }

  private onError(){
    this.snackBar.open('Erro ao salvar curso.','', { duration: 2000});
  }

  getErrorMessage(fieldName: string) {
    const field = this.form.get(fieldName);

    if (field?.hasError('required')) {
      return 'Campo obrigatório';
    }

    if (field?.hasError('minlength')) {
      const requiredLength = field.getError('minlength').requiredLength;
      return `Tamanho mínimo precisa ser de ${requiredLength} caracteres`;
    }

    if (field?.hasError('maxlength')) {
      const requiredLength = field.getError('maxlength').requiredLength;
      return `Tamanho máximo excedido de ${requiredLength} caracteres`;
    }

    return '';
  }
}
