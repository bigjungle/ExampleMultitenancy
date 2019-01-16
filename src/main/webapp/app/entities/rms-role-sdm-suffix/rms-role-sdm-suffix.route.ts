import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RmsRoleSdmSuffix } from 'app/shared/model/rms-role-sdm-suffix.model';
import { RmsRoleSdmSuffixService } from './rms-role-sdm-suffix.service';
import { RmsRoleSdmSuffixComponent } from './rms-role-sdm-suffix.component';
import { RmsRoleSdmSuffixDetailComponent } from './rms-role-sdm-suffix-detail.component';
import { RmsRoleSdmSuffixUpdateComponent } from './rms-role-sdm-suffix-update.component';
import { RmsRoleSdmSuffixDeletePopupComponent } from './rms-role-sdm-suffix-delete-dialog.component';
import { IRmsRoleSdmSuffix } from 'app/shared/model/rms-role-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class RmsRoleSdmSuffixResolve implements Resolve<IRmsRoleSdmSuffix> {
    constructor(private service: RmsRoleSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RmsRoleSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RmsRoleSdmSuffix>) => response.ok),
                map((rmsRole: HttpResponse<RmsRoleSdmSuffix>) => rmsRole.body)
            );
        }
        return of(new RmsRoleSdmSuffix());
    }
}

export const rmsRoleRoute: Routes = [
    {
        path: 'rms-role-sdm-suffix',
        component: RmsRoleSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsRole.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rms-role-sdm-suffix/:id/view',
        component: RmsRoleSdmSuffixDetailComponent,
        resolve: {
            rmsRole: RmsRoleSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsRole.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rms-role-sdm-suffix/new',
        component: RmsRoleSdmSuffixUpdateComponent,
        resolve: {
            rmsRole: RmsRoleSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsRole.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rms-role-sdm-suffix/:id/edit',
        component: RmsRoleSdmSuffixUpdateComponent,
        resolve: {
            rmsRole: RmsRoleSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsRole.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const rmsRolePopupRoute: Routes = [
    {
        path: 'rms-role-sdm-suffix/:id/delete',
        component: RmsRoleSdmSuffixDeletePopupComponent,
        resolve: {
            rmsRole: RmsRoleSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsRole.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
