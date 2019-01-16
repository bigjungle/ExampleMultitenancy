import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';

import { AccountService } from '../';
import { StateStorageService } from './state-storage.service';

@Injectable({ providedIn: 'root' })
export class CompanyRouteAccessService implements CanActivate {
    constructor(private router: Router, private accountService: AccountService, private stateStorageService: StateStorageService) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Promise<boolean> {
        const accountService = this.accountService;
        return Promise.resolve(
            accountService.identity().then(account => {
                if (account.company === null) {
                    return true;
                }
                this.stateStorageService.storeUrl(state.url);
                this.router.navigate(['accessdenied']);
            })
        );
    }
}
