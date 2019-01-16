import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ParaSourceSdmSuffix } from 'app/shared/model/para-source-sdm-suffix.model';
import { ParaSourceSdmSuffixService } from './para-source-sdm-suffix.service';
import { ParaSourceSdmSuffixComponent } from './para-source-sdm-suffix.component';
import { ParaSourceSdmSuffixDetailComponent } from './para-source-sdm-suffix-detail.component';
import { ParaSourceSdmSuffixUpdateComponent } from './para-source-sdm-suffix-update.component';
import { ParaSourceSdmSuffixDeletePopupComponent } from './para-source-sdm-suffix-delete-dialog.component';
import { IParaSourceSdmSuffix } from 'app/shared/model/para-source-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class ParaSourceSdmSuffixResolve implements Resolve<IParaSourceSdmSuffix> {
    constructor(private service: ParaSourceSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ParaSourceSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ParaSourceSdmSuffix>) => response.ok),
                map((paraSource: HttpResponse<ParaSourceSdmSuffix>) => paraSource.body)
            );
        }
        return of(new ParaSourceSdmSuffix());
    }
}

export const paraSourceRoute: Routes = [
    {
        path: 'para-source-sdm-suffix',
        component: ParaSourceSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraSource.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'para-source-sdm-suffix/:id/view',
        component: ParaSourceSdmSuffixDetailComponent,
        resolve: {
            paraSource: ParaSourceSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraSource.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'para-source-sdm-suffix/new',
        component: ParaSourceSdmSuffixUpdateComponent,
        resolve: {
            paraSource: ParaSourceSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraSource.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'para-source-sdm-suffix/:id/edit',
        component: ParaSourceSdmSuffixUpdateComponent,
        resolve: {
            paraSource: ParaSourceSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraSource.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const paraSourcePopupRoute: Routes = [
    {
        path: 'para-source-sdm-suffix/:id/delete',
        component: ParaSourceSdmSuffixDeletePopupComponent,
        resolve: {
            paraSource: ParaSourceSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraSource.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
