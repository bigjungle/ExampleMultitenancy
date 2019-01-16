import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { RmsDepSdmSuffix } from 'app/shared/model/rms-dep-sdm-suffix.model';
import { RmsDepSdmSuffixService } from './rms-dep-sdm-suffix.service';
import { RmsDepSdmSuffixComponent } from './rms-dep-sdm-suffix.component';
import { RmsDepSdmSuffixDetailComponent } from './rms-dep-sdm-suffix-detail.component';
import { RmsDepSdmSuffixUpdateComponent } from './rms-dep-sdm-suffix-update.component';
import { RmsDepSdmSuffixDeletePopupComponent } from './rms-dep-sdm-suffix-delete-dialog.component';
import { IRmsDepSdmSuffix } from 'app/shared/model/rms-dep-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class RmsDepSdmSuffixResolve implements Resolve<IRmsDepSdmSuffix> {
    constructor(private service: RmsDepSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<RmsDepSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<RmsDepSdmSuffix>) => response.ok),
                map((rmsDep: HttpResponse<RmsDepSdmSuffix>) => rmsDep.body)
            );
        }
        return of(new RmsDepSdmSuffix());
    }
}

export const rmsDepRoute: Routes = [
    {
        path: 'rms-dep-sdm-suffix',
        component: RmsDepSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsDep.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rms-dep-sdm-suffix/:id/view',
        component: RmsDepSdmSuffixDetailComponent,
        resolve: {
            rmsDep: RmsDepSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsDep.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rms-dep-sdm-suffix/new',
        component: RmsDepSdmSuffixUpdateComponent,
        resolve: {
            rmsDep: RmsDepSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsDep.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'rms-dep-sdm-suffix/:id/edit',
        component: RmsDepSdmSuffixUpdateComponent,
        resolve: {
            rmsDep: RmsDepSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsDep.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const rmsDepPopupRoute: Routes = [
    {
        path: 'rms-dep-sdm-suffix/:id/delete',
        component: RmsDepSdmSuffixDeletePopupComponent,
        resolve: {
            rmsDep: RmsDepSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.rmsDep.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
