import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ParaClassSdmSuffix } from 'app/shared/model/para-class-sdm-suffix.model';
import { ParaClassSdmSuffixService } from './para-class-sdm-suffix.service';
import { ParaClassSdmSuffixComponent } from './para-class-sdm-suffix.component';
import { ParaClassSdmSuffixDetailComponent } from './para-class-sdm-suffix-detail.component';
import { ParaClassSdmSuffixUpdateComponent } from './para-class-sdm-suffix-update.component';
import { ParaClassSdmSuffixDeletePopupComponent } from './para-class-sdm-suffix-delete-dialog.component';
import { IParaClassSdmSuffix } from 'app/shared/model/para-class-sdm-suffix.model';

@Injectable({ providedIn: 'root' })
export class ParaClassSdmSuffixResolve implements Resolve<IParaClassSdmSuffix> {
    constructor(private service: ParaClassSdmSuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ParaClassSdmSuffix> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ParaClassSdmSuffix>) => response.ok),
                map((paraClass: HttpResponse<ParaClassSdmSuffix>) => paraClass.body)
            );
        }
        return of(new ParaClassSdmSuffix());
    }
}

export const paraClassRoute: Routes = [
    {
        path: 'para-class-sdm-suffix',
        component: ParaClassSdmSuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraClass.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'para-class-sdm-suffix/:id/view',
        component: ParaClassSdmSuffixDetailComponent,
        resolve: {
            paraClass: ParaClassSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraClass.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'para-class-sdm-suffix/new',
        component: ParaClassSdmSuffixUpdateComponent,
        resolve: {
            paraClass: ParaClassSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraClass.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'para-class-sdm-suffix/:id/edit',
        component: ParaClassSdmSuffixUpdateComponent,
        resolve: {
            paraClass: ParaClassSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraClass.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const paraClassPopupRoute: Routes = [
    {
        path: 'para-class-sdm-suffix/:id/delete',
        component: ParaClassSdmSuffixDeletePopupComponent,
        resolve: {
            paraClass: ParaClassSdmSuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'plandbApp.paraClass.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
