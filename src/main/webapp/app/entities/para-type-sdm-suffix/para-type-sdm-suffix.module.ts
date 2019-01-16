import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    ParaTypeSdmSuffixComponent,
    ParaTypeSdmSuffixDetailComponent,
    ParaTypeSdmSuffixUpdateComponent,
    ParaTypeSdmSuffixDeletePopupComponent,
    ParaTypeSdmSuffixDeleteDialogComponent,
    paraTypeRoute,
    paraTypePopupRoute
} from './';

const ENTITY_STATES = [...paraTypeRoute, ...paraTypePopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ParaTypeSdmSuffixComponent,
        ParaTypeSdmSuffixDetailComponent,
        ParaTypeSdmSuffixUpdateComponent,
        ParaTypeSdmSuffixDeleteDialogComponent,
        ParaTypeSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        ParaTypeSdmSuffixComponent,
        ParaTypeSdmSuffixUpdateComponent,
        ParaTypeSdmSuffixDeleteDialogComponent,
        ParaTypeSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbParaTypeSdmSuffixModule {}
