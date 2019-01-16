import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { PlandbSharedModule } from 'app/shared';
import {
    RmsNodeSdmSuffixComponent,
    RmsNodeSdmSuffixDetailComponent,
    RmsNodeSdmSuffixUpdateComponent,
    RmsNodeSdmSuffixDeletePopupComponent,
    RmsNodeSdmSuffixDeleteDialogComponent,
    rmsNodeRoute,
    rmsNodePopupRoute
} from './';

const ENTITY_STATES = [...rmsNodeRoute, ...rmsNodePopupRoute];

@NgModule({
    imports: [PlandbSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RmsNodeSdmSuffixComponent,
        RmsNodeSdmSuffixDetailComponent,
        RmsNodeSdmSuffixUpdateComponent,
        RmsNodeSdmSuffixDeleteDialogComponent,
        RmsNodeSdmSuffixDeletePopupComponent
    ],
    entryComponents: [
        RmsNodeSdmSuffixComponent,
        RmsNodeSdmSuffixUpdateComponent,
        RmsNodeSdmSuffixDeleteDialogComponent,
        RmsNodeSdmSuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PlandbRmsNodeSdmSuffixModule {}
