import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';
import { RmsUserSdmSuffixService } from './rms-user-sdm-suffix.service';
import { RmsUserSdmSuffixComponent } from './rms-user-sdm-suffix.component';
import { RmsUserSdmSuffixDetailComponent } from './rms-user-sdm-suffix-detail.component';
import { RmsUserSdmSuffixUpdateComponent } from './rms-user-sdm-suffix-update.component';
import { RmsUserSdmSuffixDeletePopupComponent } from './rms-user-sdm-suffix-delete-dialog.component';
import { IRmsUserSdmSuffix } from 'app/shared/model/rms-user-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class RmsUserSdmSuffixResolve implements Resolve<IRmsUserSdmSuffix> {
    constructor(private service: RmsUserSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RmsUserSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RmsUserSdmSuffix>) => response.ok),
                map((rmsUser: HttpResponse<RmsUserSdmSuffix>) => rmsUser.body)
            );
        }
        return of(new RmsUserSdmSuffix());
    }
}

export const rmsUserRoute: Routes = [
    {
        path: 'rms-user-sdm-suffix',
        component: RmsUserSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rms-user-sdm-suffix/:id/view',
        component: RmsUserSdmSuffixDetailComponent,
        resolve: {
            rmsUser: RmsUserSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rms-user-sdm-suffix/new',
        component: RmsUserSdmSuffixUpdateComponent,
        resolve: {
            rmsUser: RmsUserSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rms-user-sdm-suffix/:id/edit',
        component: RmsUserSdmSuffixUpdateComponent,
        resolve: {
            rmsUser: RmsUserSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const rmsUserPopupRoute: Routes = [
    {
        path: 'rms-user-sdm-suffix/:id/delete',
        component: RmsUserSdmSuffixDeletePopupComponent,
        resolve: {
            rmsUser: RmsUserSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsUser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
