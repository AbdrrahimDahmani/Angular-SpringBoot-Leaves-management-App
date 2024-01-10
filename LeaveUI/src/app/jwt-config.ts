export const jwtConfig = {
  tokenGetter: () => localStorage.getItem('access_token'),
  allowedDomains: ['http://localhost:4200'],
  disallowedRoutes: [
    '/home',
    '/login',
    '/register',
    '/forgot-password',
    '/reset-password',
    '/logout',
    '/not-found',
  ],
};
