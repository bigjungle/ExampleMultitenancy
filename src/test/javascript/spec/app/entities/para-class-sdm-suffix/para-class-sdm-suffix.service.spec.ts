/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ParaClassSdmSuffixService } from 'app/entities/para-class-sdm-suffix/para-class-sdm-suffix.service';
import { IParaClassSdmSuffix, ParaClassSdmSuffix, ValidType } from 'app/shared/model/para-class-sdm-suffix.model';

describe('Service Tests', () => {
    describe('ParaClassSdmSuffix Service', () => {
        let injector: TestBed;
        let service: ParaClassSdmSuffixService;
        let httpMock: HttpTestingController;
        let elemDefault: IParaClassSdmSuffix;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(ParaClassSdmSuffixService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new ParaClassSdmSuffix(
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'image/png',
                'AAAAAAA',
                'AAAAAAA',
                false,
                'AAAAAAA',
                ValidType.LONG,
                currentDate,
                currentDate,
                currentDate,
                currentDate,
                currentDate
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        validBegin: currentDate.format(DATE_TIME_FORMAT),
                        validEnd: currentDate.format(DATE_TIME_FORMAT),
                        insertTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT),
                        verifyTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a ParaClassSdmSuffix', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        validBegin: currentDate.format(DATE_TIME_FORMAT),
                        validEnd: currentDate.format(DATE_TIME_FORMAT),
                        insertTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT),
                        verifyTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        validBegin: currentDate,
                        validEnd: currentDate,
                        insertTime: currentDate,
                        updateTime: currentDate,
                        verifyTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new ParaClassSdmSuffix(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a ParaClassSdmSuffix', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        serialNumber: 'BBBBBB',
                        sortString: 'BBBBBB',
                        descString: 'BBBBBB',
                        imageBlob: 'BBBBBB',
                        imageBlobName: 'BBBBBB',
                        usingFlag: true,
                        remarks: 'BBBBBB',
                        validType: 'BBBBBB',
                        validBegin: currentDate.format(DATE_TIME_FORMAT),
                        validEnd: currentDate.format(DATE_TIME_FORMAT),
                        insertTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT),
                        verifyTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        validBegin: currentDate,
                        validEnd: currentDate,
                        insertTime: currentDate,
                        updateTime: currentDate,
                        verifyTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of ParaClassSdmSuffix', async () => {
                const returnedFromService = Object.assign(
                    {
                        name: 'BBBBBB',
                        serialNumber: 'BBBBBB',
                        sortString: 'BBBBBB',
                        descString: 'BBBBBB',
                        imageBlob: 'BBBBBB',
                        imageBlobName: 'BBBBBB',
                        usingFlag: true,
                        remarks: 'BBBBBB',
                        validType: 'BBBBBB',
                        validBegin: currentDate.format(DATE_TIME_FORMAT),
                        validEnd: currentDate.format(DATE_TIME_FORMAT),
                        insertTime: currentDate.format(DATE_TIME_FORMAT),
                        updateTime: currentDate.format(DATE_TIME_FORMAT),
                        verifyTime: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        validBegin: currentDate,
                        validEnd: currentDate,
                        insertTime: currentDate,
                        updateTime: currentDate,
                        verifyTime: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a ParaClassSdmSuffix', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
