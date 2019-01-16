import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PlanInfoStepDataHisSdmSuffix } from 'app/shared/model/plan-info-step-data-his-sdm-suffix.model';
import { PlanInfoStepDataHisSdmSuffixService } from './plan-info-step-data-his-sdm-suffix.service';
import { PlanInfoStepDataHisSdmSuffixComponent } from './plan-info-step-data-his-sdm-suffix.component';
import { PlanInfoStepDataHisSdmSuffixDetailComponent } from './plan-info-step-data-his-sdm-suffix-detail.component';
import { PlanInfoStepDataHisSdmSuffixUpdateComponent } from './plan-info-step-data-his-sdm-suffix-update.component';
import { PlanInfoStepDataHisSdmSuffixDeletePopupComponent } from './plan-info-step-data-his-sdm-suffix-delete-dialog.component';
import { IPlanInfoStepDataHisSdmSuffix } from 'app/shared/model/plan-info-step-data-his-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class PlanInfoStepDataHisSdmSuffixResolve implements Resolve<IPlanInfoStepDataHisSdmSuffix> {
    constructor(private service: PlanInfoStepDataHisSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PlanInfoStepDataHisSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PlanInfoStepDataHisSdmSuffix>) => response.ok),
                map((planInfoStepDataHis: HttpResponse<PlanInfoStepDataHisSdmSuffix>) => planInfoStepDataHis.body)
            );
        }
        return of(new PlanInfoStepDataHisSdmSuffix());
    }
}

export const planInfoStepDataHisRoute: Routes = [
    {
        path: 'plan-info-step-data-his-sdm-suffix',
        component: PlanInfoStepDataHisSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepDataHis.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-step-data-his-sdm-suffix/:id/view',
        component: PlanInfoStepDataHisSdmSuffixDetailComponent,
        resolve: {
            planInfoStepDataHis: PlanInfoStepDataHisSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepDataHis.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-step-data-his-sdm-suffix/new',
        component: PlanInfoStepDataHisSdmSuffixUpdateComponent,
        resolve: {
            planInfoStepDataHis: PlanInfoStepDataHisSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepDataHis.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-step-data-his-sdm-suffix/:id/edit',
        component: PlanInfoStepDataHisSdmSuffixUpdateComponent,
        resolve: {
            planInfoStepDataHis: PlanInfoStepDataHisSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepDataHis.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const planInfoStepDataHisPopupRoute: Routes = [
    {
        path: 'plan-info-step-data-his-sdm-suffix/:id/delete',
        component: PlanInfoStepDataHisSdmSuffixDeletePopupComponent,
        resolve: {
            planInfoStepDataHis: PlanInfoStepDataHisSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepDataHis.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
