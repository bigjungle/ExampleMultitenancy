import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    PlanInfoAtchSdmSuffixComponent,
    PlanInfoAtchSdmSuffixDetailComponent,
    PlanInfoAtchSdmSuffixUpdateComponent,
    PlanInfoAtchSdmSuffixDeletePopupComponent,
    PlanInfoAtchSdmSuffixDeleteDialogComponent,
    planInfoAtchRoute,
    planInfoAtchPopupRoute
} from './';

const ENTITY_STATES = [...planInfoAtchRoute, ...planInfoAtchPopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PlanInfoAtchSdmSuffixComponent,
        PlanInfoAtchSdmSuffixDetailComponent,
        PlanInfoAtchSdmSuffixUpdateComponent,
        PlanInfoAtchSdmSuffixDeleteDialogComponent,
        PlanInfoAtchSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        PlanInfoAtchSdmSuffixComponent,
        PlanInfoAtchSdmSuffixUpdateComponent,
        PlanInfoAtchSdmSuffixDeleteDialogComponent,
        PlanInfoAtchSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbPlanInfoAtchSdmSuffixModule {}
