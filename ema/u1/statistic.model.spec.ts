import {Statistic} from './statistic.model';
import {Record} from './record.model';

describe('Statistic', () => {
  const records = [
    new Record(1, 'CS1013', 'Objektorientierte Programmierung', 6, 73, true, true, 2016),
    new Record(2, 'MN1007', 'Diskrete Mathematik', 6, 81, true, false, 2016),
    new Record(3, 'CS1019', 'Compilerbau', 6, 81, false, false, 2017),
    new Record(4, 'CS1020', 'Datenbanksysteme', 6, 92, false, false, 2017)
  ];
  const stats = new Statistic(records);

  it('should create an instance', () => {
    expect(stats).toBeTruthy();
  });

  it('counts all records', () => {
    expect(stats.recordCount).toBe(4);
  });

  it('counts half weighted records', () => {
    expect(stats.hwCount).toBe(2);
  });

  it('sums up credit points', () => {
    expect(stats.sumCrp).toBe(24);
  });

  it('calculates missing credit points to graduation', () => {
    expect(stats.crpToEnd).toBe(156);
  });

  it('calculates average grade', () => {
    expect(stats.averageGrade).toBe(83);
  });

});
