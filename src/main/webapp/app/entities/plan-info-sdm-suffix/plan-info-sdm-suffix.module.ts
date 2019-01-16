import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    PlanInfoSdmSuffixComponent,
    PlanInfoSdmSuffixDetailComponent,
    PlanInfoSdmSuffixUpdateComponent,
    PlanInfoSdmSuffixDeletePopupComponent,
    PlanInfoSdmSuffixDeleteDialogComponent,
    planInfoRoute,
    planInfoPopupRoute
} from './';

const ENTITY_STATES = [...planInfoRoute, ...planInfoPopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PlanInfoSdmSuffixComponent,
        PlanInfoSdmSuffixDetailComponent,
        PlanInfoSdmSuffixUpdateComponent,
        PlanInfoSdmSuffixDeleteDialogComponent,
        PlanInfoSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        PlanInfoSdmSuffixComponent,
        PlanInfoSdmSuffixUpdateComponent,
        PlanInfoSdmSuffixDeleteDialogComponent,
        PlanInfoSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbPlanInfoSdmSuffixModule {}
