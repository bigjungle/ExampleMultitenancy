import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ParaAttrSdmSuffix } from 'app/shared/model/para-attr-sdm-suffix.model';
import { ParaAttrSdmSuffixService } from './para-attr-sdm-suffix.service';
import { ParaAttrSdmSuffixComponent } from './para-attr-sdm-suffix.component';
import { ParaAttrSdmSuffixDetailComponent } from './para-attr-sdm-suffix-detail.component';
import { ParaAttrSdmSuffixUpdateComponent } from './para-attr-sdm-suffix-update.component';
import { ParaAttrSdmSuffixDeletePopupComponent } from './para-attr-sdm-suffix-delete-dialog.component';
import { IParaAttrSdmSuffix } from 'app/shared/model/para-attr-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class ParaAttrSdmSuffixResolve implements Resolve<IParaAttrSdmSuffix> {
    constructor(private service: ParaAttrSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ParaAttrSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ParaAttrSdmSuffix>) => response.ok),
                map((paraAttr: HttpResponse<ParaAttrSdmSuffix>) => paraAttr.body)
            );
        }
        return of(new ParaAttrSdmSuffix());
    }
}

export const paraAttrRoute: Routes = [
    {
        path: 'para-attr-sdm-suffix',
        component: ParaAttrSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraAttr.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'para-attr-sdm-suffix/:id/view',
        component: ParaAttrSdmSuffixDetailComponent,
        resolve: {
            paraAttr: ParaAttrSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraAttr.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'para-attr-sdm-suffix/new',
        component: ParaAttrSdmSuffixUpdateComponent,
        resolve: {
            paraAttr: ParaAttrSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraAttr.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'para-attr-sdm-suffix/:id/edit',
        component: ParaAttrSdmSuffixUpdateComponent,
        resolve: {
            paraAttr: ParaAttrSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraAttr.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const paraAttrPopupRoute: Routes = [
    {
        path: 'para-attr-sdm-suffix/:id/delete',
        component: ParaAttrSdmSuffixDeletePopupComponent,
        resolve: {
            paraAttr: ParaAttrSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraAttr.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
