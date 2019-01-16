import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ParaPropSdmSuffix } from 'app/shared/model/para-prop-sdm-suffix.model';
import { ParaPropSdmSuffixService } from './para-prop-sdm-suffix.service';
import { ParaPropSdmSuffixComponent } from './para-prop-sdm-suffix.component';
import { ParaPropSdmSuffixDetailComponent } from './para-prop-sdm-suffix-detail.component';
import { ParaPropSdmSuffixUpdateComponent } from './para-prop-sdm-suffix-update.component';
import { ParaPropSdmSuffixDeletePopupComponent } from './para-prop-sdm-suffix-delete-dialog.component';
import { IParaPropSdmSuffix } from 'app/shared/model/para-prop-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class ParaPropSdmSuffixResolve implements Resolve<IParaPropSdmSuffix> {
    constructor(private service: ParaPropSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ParaPropSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ParaPropSdmSuffix>) => response.ok),
                map((paraProp: HttpResponse<ParaPropSdmSuffix>) => paraProp.body)
            );
        }
        return of(new ParaPropSdmSuffix());
    }
}

export const paraPropRoute: Routes = [
    {
        path: 'para-prop-sdm-suffix',
        component: ParaPropSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraProp.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'para-prop-sdm-suffix/:id/view',
        component: ParaPropSdmSuffixDetailComponent,
        resolve: {
            paraProp: ParaPropSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraProp.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'para-prop-sdm-suffix/new',
        component: ParaPropSdmSuffixUpdateComponent,
        resolve: {
            paraProp: ParaPropSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraProp.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'para-prop-sdm-suffix/:id/edit',
        component: ParaPropSdmSuffixUpdateComponent,
        resolve: {
            paraProp: ParaPropSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraProp.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const paraPropPopupRoute: Routes = [
    {
        path: 'para-prop-sdm-suffix/:id/delete',
        component: ParaPropSdmSuffixDeletePopupComponent,
        resolve: {
            paraProp: ParaPropSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraProp.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
