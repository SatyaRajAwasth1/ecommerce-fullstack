import { CanActivateFn } from '@angular/router';

export const authguardGuard: CanActivateFn = (route, state) => {
  return true;
};
