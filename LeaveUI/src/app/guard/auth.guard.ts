import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
export const authGuard: CanActivateFn = (route, state) => {
  const cookie = inject(CookieService);
  const token = cookie.get('access_token');
  const role = cookie.get('role');
  const router = inject(Router);
  if (cookie.check('access_token') && token.length > 0 && role === 'Employer') {
    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }
};
