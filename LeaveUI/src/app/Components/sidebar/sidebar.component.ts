import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
})
export class SidebarComponent {
  constructor(private cookieService: CookieService, private router: Router) {}
  logout() {
    this.cookieService.deleteAll();
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}
