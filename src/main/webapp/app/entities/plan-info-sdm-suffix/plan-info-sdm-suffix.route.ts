import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PlanInfoSdmSuffix } from 'app/shared/model/plan-info-sdm-suffix.model';
import { PlanInfoSdmSuffixService } from './plan-info-sdm-suffix.service';
import { PlanInfoSdmSuffixComponent } from './plan-info-sdm-suffix.component';
import { PlanInfoSdmSuffixDetailComponent } from './plan-info-sdm-suffix-detail.component';
import { PlanInfoSdmSuffixUpdateComponent } from './plan-info-sdm-suffix-update.component';
import { PlanInfoSdmSuffixDeletePopupComponent } from './plan-info-sdm-suffix-delete-dialog.component';
import { IPlanInfoSdmSuffix } from 'app/shared/model/plan-info-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class PlanInfoSdmSuffixResolve implements Resolve<IPlanInfoSdmSuffix> {
    constructor(private service: PlanInfoSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PlanInfoSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PlanInfoSdmSuffix>) => response.ok),
                map((planInfo: HttpResponse<PlanInfoSdmSuffix>) => planInfo.body)
            );
        }
        return of(new PlanInfoSdmSuffix());
    }
}

export const planInfoRoute: Routes = [
    {
        path: 'plan-info-sdm-suffix',
        component: PlanInfoSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-sdm-suffix/:id/view',
        component: PlanInfoSdmSuffixDetailComponent,
        resolve: {
            planInfo: PlanInfoSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-sdm-suffix/new',
        component: PlanInfoSdmSuffixUpdateComponent,
        resolve: {
            planInfo: PlanInfoSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-sdm-suffix/:id/edit',
        component: PlanInfoSdmSuffixUpdateComponent,
        resolve: {
            planInfo: PlanInfoSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfo.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const planInfoPopupRoute: Routes = [
    {
        path: 'plan-info-sdm-suffix/:id/delete',
        component: PlanInfoSdmSuffixDeletePopupComponent,
        resolve: {
            planInfo: PlanInfoSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfo.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
