import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ParaTypeSdmSuffix } from 'app/shared/model/para-type-sdm-suffix.model';
import { ParaTypeSdmSuffixService } from './para-type-sdm-suffix.service';
import { ParaTypeSdmSuffixComponent } from './para-type-sdm-suffix.component';
import { ParaTypeSdmSuffixDetailComponent } from './para-type-sdm-suffix-detail.component';
import { ParaTypeSdmSuffixUpdateComponent } from './para-type-sdm-suffix-update.component';
import { ParaTypeSdmSuffixDeletePopupComponent } from './para-type-sdm-suffix-delete-dialog.component';
import { IParaTypeSdmSuffix } from 'app/shared/model/para-type-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class ParaTypeSdmSuffixResolve implements Resolve<IParaTypeSdmSuffix> {
    constructor(private service: ParaTypeSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ParaTypeSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ParaTypeSdmSuffix>) => response.ok),
                map((paraType: HttpResponse<ParaTypeSdmSuffix>) => paraType.body)
            );
        }
        return of(new ParaTypeSdmSuffix());
    }
}

export const paraTypeRoute: Routes = [
    {
        path: 'para-type-sdm-suffix',
        component: ParaTypeSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'para-type-sdm-suffix/:id/view',
        component: ParaTypeSdmSuffixDetailComponent,
        resolve: {
            paraType: ParaTypeSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'para-type-sdm-suffix/new',
        component: ParaTypeSdmSuffixUpdateComponent,
        resolve: {
            paraType: ParaTypeSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraType.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'para-type-sdm-suffix/:id/edit',
        component: ParaTypeSdmSuffixUpdateComponent,
        resolve: {
            paraType: ParaTypeSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraType.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const paraTypePopupRoute: Routes = [
    {
        path: 'para-type-sdm-suffix/:id/delete',
        component: ParaTypeSdmSuffixDeletePopupComponent,
        resolve: {
            paraType: ParaTypeSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraType.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
