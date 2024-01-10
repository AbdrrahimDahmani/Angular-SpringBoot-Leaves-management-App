import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-hr-sidebar',
  templateUrl: './hr-sidebar.component.html',
  styleUrls: ['./hr-sidebar.component.css'],
})
export class HrSidebarComponent {
  constructor(private cookieService: CookieService, private router: Router) {}
  logout() {
    this.cookieService.deleteAll();
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}
