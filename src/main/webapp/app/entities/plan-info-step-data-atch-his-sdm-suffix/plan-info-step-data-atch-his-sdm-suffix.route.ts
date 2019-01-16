import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PlanInfoStepDataAtchHisSdmSuffix } from 'app/shared/model/plan-info-step-data-atch-his-sdm-suffix.model';
import { PlanInfoStepDataAtchHisSdmSuffixService } from './plan-info-step-data-atch-his-sdm-suffix.service';
import { PlanInfoStepDataAtchHisSdmSuffixComponent } from './plan-info-step-data-atch-his-sdm-suffix.component';
import { PlanInfoStepDataAtchHisSdmSuffixDetailComponent } from './plan-info-step-data-atch-his-sdm-suffix-detail.component';
import { PlanInfoStepDataAtchHisSdmSuffixUpdateComponent } from './plan-info-step-data-atch-his-sdm-suffix-update.component';
import { PlanInfoStepDataAtchHisSdmSuffixDeletePopupComponent } from './plan-info-step-data-atch-his-sdm-suffix-delete-dialog.component';
import { IPlanInfoStepDataAtchHisSdmSuffix } from 'app/shared/model/plan-info-step-data-atch-his-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class PlanInfoStepDataAtchHisSdmSuffixResolve implements Resolve<IPlanInfoStepDataAtchHisSdmSuffix> {
    constructor(private service: PlanInfoStepDataAtchHisSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PlanInfoStepDataAtchHisSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<PlanInfoStepDataAtchHisSdmSuffix>) => response.ok),
                map((planInfoStepDataAtchHis: HttpResponse<PlanInfoStepDataAtchHisSdmSuffix>) => planInfoStepDataAtchHis.body)
            );
        }
        return of(new PlanInfoStepDataAtchHisSdmSuffix());
    }
}

export const planInfoStepDataAtchHisRoute: Routes = [
    {
        path: 'plan-info-step-data-atch-his-sdm-suffix',
        component: PlanInfoStepDataAtchHisSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepDataAtchHis.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-step-data-atch-his-sdm-suffix/:id/view',
        component: PlanInfoStepDataAtchHisSdmSuffixDetailComponent,
        resolve: {
            planInfoStepDataAtchHis: PlanInfoStepDataAtchHisSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepDataAtchHis.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-step-data-atch-his-sdm-suffix/new',
        component: PlanInfoStepDataAtchHisSdmSuffixUpdateComponent,
        resolve: {
            planInfoStepDataAtchHis: PlanInfoStepDataAtchHisSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepDataAtchHis.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'plan-info-step-data-atch-his-sdm-suffix/:id/edit',
        component: PlanInfoStepDataAtchHisSdmSuffixUpdateComponent,
        resolve: {
            planInfoStepDataAtchHis: PlanInfoStepDataAtchHisSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepDataAtchHis.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const planInfoStepDataAtchHisPopupRoute: Routes = [
    {
        path: 'plan-info-step-data-atch-his-sdm-suffix/:id/delete',
        component: PlanInfoStepDataAtchHisSdmSuffixDeletePopupComponent,
        resolve: {
            planInfoStepDataAtchHis: PlanInfoStepDataAtchHisSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.planInfoStepDataAtchHis.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
