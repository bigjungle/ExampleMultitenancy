import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ParaStateSdmSuffix } from 'app/shared/model/para-state-sdm-suffix.model';
import { ParaStateSdmSuffixService } from './para-state-sdm-suffix.service';
import { ParaStateSdmSuffixComponent } from './para-state-sdm-suffix.component';
import { ParaStateSdmSuffixDetailComponent } from './para-state-sdm-suffix-detail.component';
import { ParaStateSdmSuffixUpdateComponent } from './para-state-sdm-suffix-update.component';
import { ParaStateSdmSuffixDeletePopupComponent } from './para-state-sdm-suffix-delete-dialog.component';
import { IParaStateSdmSuffix } from 'app/shared/model/para-state-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class ParaStateSdmSuffixResolve implements Resolve<IParaStateSdmSuffix> {
    constructor(private service: ParaStateSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ParaStateSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ParaStateSdmSuffix>) => response.ok),
                map((paraState: HttpResponse<ParaStateSdmSuffix>) => paraState.body)
            );
        }
        return of(new ParaStateSdmSuffix());
    }
}

export const paraStateRoute: Routes = [
    {
        path: 'para-state-sdm-suffix',
        component: ParaStateSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraState.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'para-state-sdm-suffix/:id/view',
        component: ParaStateSdmSuffixDetailComponent,
        resolve: {
            paraState: ParaStateSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraState.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'para-state-sdm-suffix/new',
        component: ParaStateSdmSuffixUpdateComponent,
        resolve: {
            paraState: ParaStateSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraState.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'para-state-sdm-suffix/:id/edit',
        component: ParaStateSdmSuffixUpdateComponent,
        resolve: {
            paraState: ParaStateSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraState.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const paraStatePopupRoute: Routes = [
    {
        path: 'para-state-sdm-suffix/:id/delete',
        component: ParaStateSdmSuffixDeletePopupComponent,
        resolve: {
            paraState: ParaStateSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraState.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
