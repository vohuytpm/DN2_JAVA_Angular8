import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { CategoryService } from "../category.service";
import { Category } from "../category";
import { Router } from '@angular/router';
@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent implements OnInit {
  categories: Observable<Category[]>;

  constructor(private categoryService: CategoryService,
    private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.categories = this.categoryService.getCategoriesList();
  }

  deleteCategory(id: number) {
    this.categoryService.deleteCategory(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  categoryDetails(id: number){
    this.router.navigate(['Categorydetails', id]);
  }
  updateCategory(id: number){
    this.router.navigate(['updateCategory', id]);
  }
}
