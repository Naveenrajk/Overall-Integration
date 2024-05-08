import { Component } from '@angular/core';
import { OptionResponse } from '../model/OptionResponse';
import { OptionResponseNew } from '../model/OptionResponseNew';
import { Reaction } from '../model/Reaction';
import { User } from '../model/User';
import { Poll } from '../model/poll';
import { Option } from '../model/Option';
import { PollService } from '../Admin-Service/poll.service';
import { ReactionService } from '../Admin-Service/reaction.service';


@Component({
  selector: 'app-draft',
  templateUrl: './draft.component.html',
  styleUrls: ['./draft.component.scss'],
})
export class DraftComponent {
  Id: OptionResponse;

  result: string = '';

  results: string = '';

  optionResponse: any;

  lists: any;

  selected!: number;
  votingEnded = false;

  comments!: string;

  count!: number;

  polls: Poll[] = [];

  option: Option[] = [];

  users: User[] = [];

  date: Date | undefined;

  reaction: Reaction[] = [];

  Option: Option;

  OptionResponse: any;

  OptionResponseNew: any;

  optionId: any;
  User: any;

  Poll: any;

  optCount: number = 0;

  reactionData: Reaction;

  Reaction: number = 0;

  reactCount: number = 0;

  responseCount: number = 0;

  optionCount: any;

  TotalnumberofLikes: { [pollId: number]: number } = {};
  TotalnumberofDisLikes: number = 25;
  flag: number = 0;
  dislikeS: string = 'dislike-button';
  likeS: string = 'like-button';

  TotalnumberofVotes: number = 0;
  TotalnumberofNotVoted: number = 25;
  flags: number = 0;
  NotVoted: string = 'Vote-button';
  Voted: string = 'Voted-button';
  reactionFlag: number = 0;

  isShow = true;

  isLike = false;

  isOptionIdHide = true;

  isHide = true;

  isView = true;

  isViewHide = false;

  optionData: any;

  orn: OptionResponseNew = new OptionResponseNew();

  votes: { [key: string]: number } = {};

  totalVotes: any;

  rn: Reaction = new Reaction();

  ngOnInit() {
    this.pollService.getDraft().subscribe((AllPoll) => {
      this.polls = AllPoll;

      console.log(this.polls);
    });
    this.pollService.getOptionResponse().subscribe((Response) => {
      this.OptionResponse = Response;
      console.log(this.OptionResponse);
    });
    this.count = 0;
  }
  constructor(
    private pollService: PollService,
    private reactionService: ReactionService
  ) {
    setInterval(() => {
      this.date = new Date();
    }, 1000);

    this.Id = new OptionResponse();
    this.OptionResponse = new OptionResponse();
    this.optionData = new Option();
    this.optionId = new OptionResponse();
    this.reactionData = new Reaction();
    this.Option = new Option();
    this.Reaction;
  }

  toggleView() {
    this.isView = !this.isView;
    this.isViewHide = !this.isViewHide;
  }
  toggleDisplay() {
    this.isShow = !this.isShow;
    this.isLike = !this.isLike;
  }

  toggleDisplayComment() {
    this.isHide = !this.isHide;
  }
  getdraft() {
    console.log('get method');
    this.pollService.getDraft().subscribe(() => {
      // this.polls = AllPoll;
      this.pollService.getOption().subscribe((save) => {
        this.option = save;
        console.log(this.option);
      });
    });
  }

  optionResponseInsert(data: any) {
    console.log(data.optionId);
    this.orn.optionId = data.optionId;
    this.orn.userid = 1;
    this.orn.pollId = data.pollId;

    this.optCount = data.optionId;

    console.log(this.optionCount);
    this.result = this.pollService.getOptionResponseInsert(this.orn);

    if (!this.votes[data.optionId]) {
      this.votes[data.optionId] = 1;
      console.log(this.votes);
    } else {
      this.votes[data.optionId]++;
      console.log(this.votes);
    }
  }

  getTotalVotes() {
    return Object.values(this.votes).reduce((acc, curr) => acc + curr, 0);
  }

  calculatePercentage(optionId: any) {
    this.totalVotes = this.getTotalVotes();
    return this.totalVotes > 0
      ? (this.votes[optionId] / this.totalVotes) * 100
      : 0;
  }
  receiveComment($event: any) {
    this.comments = $event;
    this.count = this.comments.length;
    console.log(this.comments.length);
  }

  recieveCount($event: any) {
    this.comments = $event;
    this.count = this.comments.length;
  }
  reactionInsert(pollId: number) {
    console.log(pollId);
    this.rn.pollId = pollId;
    this.rn.userId = 1;
    if (this.TotalnumberofLikes[pollId]) {
      delete this.TotalnumberofLikes[pollId];
      this.results = this.reactionService.reactionDelete(pollId);
    } else {
      this.TotalnumberofLikes[pollId] = 1;
      this.results = this.reactionService.reactionInsert(this.rn);
    }
    console.log(this.results);
    if (this.TotalnumberofLikes[pollId]) {
      this.TotalnumberofLikes[pollId] = this.reactCount;
    } else {
      this.TotalnumberofLikes[pollId]--;
    }
  }
}
