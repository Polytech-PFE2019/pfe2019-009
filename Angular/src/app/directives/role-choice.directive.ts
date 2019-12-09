import {AfterContentInit, Directive, ElementRef, HostListener, Input} from '@angular/core';

@Directive({
  selector: '[appSignal]'
})
export class RoleChoiceDirective {
  @Input() current: any;
  @Input() max: any;
  isChoosed = false;
  innerText = '';
  previous: ElementRef = null;
  numOfPlayers = 0;

  constructor(private el: ElementRef) {
    console.log(this.el);
    if (this.max - this.current <= 5) {
      this.el.nativeElement.style.backgroundColor = '#C50E05';
    } else if (this.max - this.current > 15) {
      this.el.nativeElement.style.backgroundColor = '#008000';
    } else {
      this.el.nativeElement.style.backgroundColor = '#F68C12';
    }
  }

  @HostListener('mouseover') onMouseEnter() {
    // if (this.max - this.current <= 5) {
    //   this.el.nativeElement.style.backgroundColor = '#C50E05';
    // } else if (this.max - this.current > 15) {
    //   this.el.nativeElement.style.backgroundColor = '#008000';
    // } else {
    //   this.el.nativeElement.style.backgroundColor = '#F68C12';
    // }
    console.log('testtst');
  }


}
