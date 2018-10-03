export abstract class AbstractTableView<T> {

  actualData: Array<T> = [];
  displayData: Array<T> = [];

  filteringValues: {};
  filteredValues: {};

  loading: boolean;
  sortKey: string;
  sortValue: string;

  sort(sort: { key: string, value: string }): void {
    this.loading = true;
    this.sortKey = sort.key;
    this.sortValue = sort.value;
    this.setDisplayData();
  }

  abstract setFilterColumns();

  filterData(searchField: string, searchValues: Array<string>) {
    this.loading = true;
    let displayData = [...this.actualData];

    this.filteredValues[searchField] = searchValues;

    displayData = displayData.filter(dElem => {
      let stat = true;
      /*Object.keys(this.filteredValues).forEach(filterKey => {
        stat  = stat && Object.keys(this.filteredValues[filterKey]).some(val => dElem[filterKey] === val);
      });*/

      Object.keys(this.filteredValues).forEach(filterKey => {
        if (this.filteredValues[filterKey].length > 0) {
          stat = stat && this.filteredValues[filterKey].some(key => dElem[filterKey] === key);
        }
      });
      return stat;
    });
    this.displayData = displayData;
    this.setDisplayData();
  }

  setDisplayData() {
    const dataList = [...this.displayData];
    if (this.sortKey && this.sortValue) {
      this.displayData = [...dataList.sort((a, b) => (this.sortValue === 'ascend')
        ? (a[this.sortKey] > b[this.sortKey] ? 1 : -1) : (b[this.sortKey] > a[this.sortKey] ? 1 : -1))];
    } else {
      this.displayData = [...dataList];
    }
    this.loading = false;

  }

  setData(data: Array<T>) {
    this.actualData = data;
    this.displayData = data;
    this.filteredValues = {};
    if (data.length > 0) {
      Object.keys(data[0]).forEach(key => {
        this.filteringValues[key] = data.map(elem => new FilterColumn(elem[key]));
      });
    }
  }

}

export class FilterColumn {
  constructor(value: string) {
    this.text = this.value = value;
  }
  text: string;
  value: string;
}
