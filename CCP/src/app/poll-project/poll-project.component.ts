import { ChangeDetectorRef, Component, Input } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { CommentsService } from 'src/app/comments/services/comments.service';
import { ActiveCommentInterface } from 'src/app/comments/types/activeComment.interface';
import { CommentInterface } from 'src/app/comments/types/comment.interface';
import { OptionResponse } from 'src/app/model/OptionResponse';
import { OptionResponseNew } from 'src/app/model/OptionResponseNew';
import { PollDTO } from 'src/app/model/PollDTO';
import { PollResponse } from 'src/app/model/PollResponse';
import { PollValue } from 'src/app/model/PollValue';
import { Reaction } from 'src/app/model/Reaction';
import { User } from 'src/app/model/User';
import { Poll } from 'src/app/model/poll';
import { Option } from 'src/app/model/Option';
import { PollService } from '../Services/poll.service';
import { ReactionService } from '../Services/reaction.service';

@Component({
  selector: 'app-poll-project',
  templateUrl: './poll-project.component.html',
  styleUrls: ['./poll-project.component.css']
})
export class PollProjectComponent {
  @Input() currentUserId!: string;
  @Input() pollArray: Poll[] = [];
  @Input() Allpoll!: Poll;
  @Input() pollId!: number;

  Id!: OptionResponse;

  result: string = "";

  results: string = "";

  optionResponse: any;

  lists: any;

  selected!: number;

  votingEnded = false;

  count!: number;

  polls: Poll[] = [];

  option: Option[] = [];

  users: User[] = [];

  date: Date | undefined;

  reaction: Reaction[] = [];

  Option!: Option;

  OptionResponse: any;

  OptionResponseNew: any;

  optionId: any;

  User: any;

  Poll: any;

  optCount: number = 0;

  reactionData!: Reaction;

  Reaction: any;

  reactCount: number = 0;

  responseCount: number = 0;

  count1: number = 0;

  optionCount: any;

  optionData: any;

  userId: any;

  regionId: any;

  projectId : any;

  departmentId : any;

  pollResponse: PollResponse;

  pollsResponse: PollResponse;
  pollsResponse1: PollResponse[] = [];

  comments: CommentInterface[] = [];

  comment!: CommentInterface;

  activeComment: ActiveCommentInterface | null = null;

  poll!: Poll;

  isHide: boolean[] = Array(this.polls.length).fill(true);

  ac: CommentInterface = new CommentInterface();

  TotalnumberofLikes: { [pollId: number]: number } = {};

  flag: number = 0;

  dislikeS: string = "dislike-button";

  likeS: string = "like-button";

  TotalnumberofVotes: number = 0;

  flags: number = 0;

  NotVoted: string = "Vote-button";

  Voted: string = "Voted-button";

  reactionFlag: number = 0;

  voteCounts: { [key: number]: number } = {};

  isShow = true;

  isLike = false;

  pollValue: number = 0;

  isOptionIdHide = true;

  isView = true;

  isViewHide = false;

  orn: OptionResponseNew = new OptionResponseNew();

  votes: { [key: string]: number } = {};

  totalVotes: any;

  rn: Reaction = new Reaction();

  userValue: number = 0;

  view: boolean = false;

  isVisible = true;

  Hide = true;

  Show = true;

  isHideComment = true;

  TotalNumberofVotes!: number;

  changeOption() {}

  voted: boolean = false;

  responseId!: number;

  searchPoll: string = "";

  pollarray: Poll[] = [];

  display = true;

  public pollForm: FormGroup;

  pollData: PollDTO;

  pl: PollValue = new PollValue();

  pollQuestion: any;

  optionEditValue: string[] = [];

  OptionList: Option[] = [];

  optionValue: any;

  values: any[] = [];

  num: number = 0;

  valuesForm!: FormGroup;

  
  ngOnChange() {
    this.commentsService.getComments().subscribe((comments) => {
      this.comments = comments;
    });

    
  }
  ngOnInit() {
    this.initForm();
    this.userId = sessionStorage.getItem("userId") || "";
    this.userValue = parseInt(this.userId);

    this.regionId = sessionStorage.getItem("regionId") || "";

    this.departmentId = sessionStorage.getItem("departmentId") || "";

    this.projectId = sessionStorage.getItem("projectId") || "";

    this.pollService.getAllPolls().subscribe((AllPoll) => {
      this.polls = AllPoll;
    });
    this.pollService.getOption().subscribe((option) => {
      this.option = option;
    });

    this.count = 0;

    this.commentsService.getComments().subscribe((comments) => {
      this.comments = comments;
    });

    this.pollService.getAllResponse(this.userValue).subscribe((AllPoll) => {
      this.pollsResponse1 = AllPoll;
      console.log(this.pollsResponse1);
    });

    this.getTotalVoteCount();

    const voteCountsString = localStorage.getItem('votes');
    this.votes = voteCountsString ? JSON.parse(voteCountsString) : {};
    console.log(voteCountsString)
    console.log(this.votes)

   
    
    
    this.pollService.getAllVoteCount().subscribe((count)=>{
      this.totalVotes = count;
     
      // localStorage.setItem('votes', JSON.stringify(this.votes));
      
  });

    
  }
  

  constructor(private pollService: PollService, private reactionService: ReactionService, private commentsService: CommentsService,
              private _activatedRoute: ActivatedRoute, private _fb: FormBuilder, private cdref: ChangeDetectorRef) {
    
    setInterval(() => {
      this.date = new Date();
    }, 1000);
    this.Id = new OptionResponse();
    this.OptionResponse = new OptionResponse();
    this.optionData = new Option();
    this.optionId = new OptionResponse();
    this.reactionData = new Reaction();
    this.Option = new Option();

    this.pollResponse = new PollResponse();
    this.pollsResponse = new PollResponse();
    this.pollData = new PollDTO();

    this.Reaction;
    this.changeStatus();

    this.pollForm = this._fb.group({
      pollQuestion: new FormControl("", [Validators.required]),
    });
    this.userId = sessionStorage.getItem("userId") || "";
    this.pollService.getAllResponse(this.userId).subscribe((AllPoll) => {
      this.pollsResponse1 = AllPoll;
      console.log(this.pollsResponse1);
    });

 

    

  }


  toggleDisplayView() {
    this.Show = !this.Show;
    this.Hide = !this.Hide;
  }

  refreshPage() {
    location.reload();
  }

  callView() {
    this.isVisible = false;
    this.view = true;
  }

  toggleView() {
    this.isView = !this.isView;
    this.isViewHide = !this.isViewHide;
  }
  toggleDisplay() {
    this.isShow = !this.isShow;
    this.isLike = !this.isLike;
  }

  addCommentById({
    text,
    parentId,
    pollId,
    userId,
  }: {
    text: any;
    parentId: any | null;
    pollId: any;
    userId: any;
  }): void {
    this.commentsService
      .createComment(text, parentId, pollId, userId)
      .subscribe();
    this.ngOnChange();
    this.ngOnChange();
    this.ngOnChange();
  }

  getRootComments(pollId: number): CommentInterface[] {
    return this.comments.filter(
      (comment) => comment.parentId == "0" && comment.poll.pollId === pollId
    );
  }

  updateComment({
    text,
    commentId,
  }: {
    text: string;
    commentId: string;
  }): void {
    this.commentsService
      .updateComment(commentId, text)
      .subscribe((updatedComment) => {
        this.comments = this.comments.map((comment) => {
          if (comment.id === commentId) {
            return updatedComment;
          }
          return comment;
        });
        this.activeComment = null;
      });
  }

  deleteComment(commentId: string): void {
    this.commentsService.deleteComment(commentId).subscribe(() => {
      this.comments = this.comments.filter(
        (comment) => comment.id !== commentId
      );
    });
  }

  getCommentsCount(pollId: number): number {
    return this.comments.filter((comment) => comment.poll.pollId === pollId)
      .length;
  }

  setActiveComment(activeComment: ActiveCommentInterface | null): void {
    this.activeComment = activeComment;
  }

  addComment({
    text,
    parentId,
    userId,
    pollId: number,
  }: {
    text: string;
    parentId: string | null;
    userId: any;
    pollId: number;
  }): void {
    this.commentsService
      .createComment(text, parentId, this.pollId, userId)
      .subscribe((createdComment) => {
        this.comments = [...this.comments, createdComment];
        this.activeComment = null;
      });
  }

  getReplies(commentId: string): CommentInterface[] {
    return this.comments
      .filter((comment) => comment.parentId === commentId)
      .sort(
        (a, b) =>
          new Date(a.createdAt).getTime() - new Date(b.createdAt).getTime()
      );
  }

  getPoll(id: any) {
    console.log(id);
    this.pollValue = id;
    this.pollResponse.pollId = id;
    this.pollResponse.user = this.userValue;

    this.pollService.getResponse(this.pollResponse).subscribe((AllPoll) => {
      this.pollsResponse = AllPoll;
      console.log(this.pollsResponse);
    });

    this.reactionService.getAllReactionCount(id).subscribe((ReactionCount) => {
      this.reactCount = ReactionCount;
    });

    this.commentsService.getComments().subscribe((comments) => {
      this.comments = comments;
    });
  }

  getPollId(id: any, question: any, value: any) {
    this.pollValue = id;
    this.pollQuestion = question;

    console.log(value)
    this.values = this.option
      .filter((opt) => opt.pollId === this.pollValue)
      .map((opt) => ({ value: opt.value, optionId: opt.optionId }));
      this.cdref.detectChanges();

    console.log(this.pollValue);
    console.log(this.pollQuestion);
    console.log(this.values);
  }

  initForm(): void {
    this.valuesForm = this._fb.group({});
        this.values.forEach((_, index) => {
          this.valuesForm.addControl(
            'valueGroup${index}',
            this.valueFormGroup(index)
          );
        });
      }
     
      valueFormGroup(index: number): FormGroup {
      return this._fb.group({
          value: [this.values[index].value, Validators.required],
        });
      }

  toggleDisplayComment(pollId: number) {
    this.isHide[pollId] = !this.isHide[pollId];
    this.isHideComment = !this.isHideComment;
  }

  receiveComment($event: any) {
    this.comments = $event;
    this.count = this.comments.length;
  }

  recieveCount($event: any) {
    this.comments = $event;
    this.count = this.comments.length;
  }

  reactionInsert(pollId: number) {
    this.rn.pollId = pollId;
    this.rn.userId = this.userValue;
    if (this.TotalnumberofLikes[pollId]) {
      delete this.TotalnumberofLikes[pollId];
      this.results = this.reactionService.reactionDeleteByUser(this.rn);
    } else {
      this.TotalnumberofLikes[pollId] = 1;
      this.results = this.reactionService.reactionInsert(this.rn);
    }

    if (this.TotalnumberofLikes[pollId]) {
      this.TotalnumberofLikes[pollId] = this.reactCount + 1;
    } else {
      this.TotalnumberofLikes[pollId]--;
    }
  }

  getVote(id: any) {
    this.pollResponse.pollId = id;
    this.pollResponse.user = this.userValue;

   
    this.pollService.getResponse(this.pollResponse).subscribe((AllPoll) => {
      this.pollsResponse = AllPoll;
      console.log(this.pollsResponse);
    });

    this.pollService.getAllResponse(this.userId).subscribe((AllPoll) => {
      this.pollsResponse1 = AllPoll;
      console.log(this.pollsResponse1);
    });
  }
  vote(pollId: any, optionId: any, userValue: any) {
    this.pollValue = pollId;
    this.optionValue = optionId;
    this.num = 0;

    

    if (this.pollsResponse1.length == 0) {
      console.log("insert");
      this.pollService.getAllVoteCount().subscribe((count)=>{
        this.votes = count;
        localStorage.setItem('votes', JSON.stringify(this.votes));
  
        console.log(this.votes)
    });
  
      this.pollService.vote(pollId, optionId, userValue).subscribe(() => {
        this.updateVoteCounts();
        this.getVote(pollId);
      });
    } else if (this.pollsResponse1.length > 0) {
      for (let i = 0; i < this.pollsResponse1.length; i++) {    
        console.log(this.pollsResponse1[i]);
        if (
          this.pollsResponse1[i].user == this.userValue &&
          this.pollsResponse1[i].response == true &&
          this.pollsResponse1[i].pollId == pollId
        ) {
          this.pollService
            .updateVote(optionId, userValue, pollId)
            .subscribe(() => {
              this.updateVoteCounts();
            });
          this.num = +1;
          break;
        }
      }
      if (this.num == 0) {
        for (let i = 0; i < this.pollsResponse1.length; i++) {
          if (
            this.pollsResponse1[i].user == this.userValue &&
            this.pollsResponse1[i].pollId != pollId
          ) {
            this.pollService.vote(pollId, optionId, userValue).subscribe(() => {
              this.updateVoteCounts();
              this.getVote(pollId);
            });
            break;
          }
        }
      }
    }
  }

  updateVoteCounts() {
    this.pollService.getVoteCounts(this.pollValue).subscribe((data) => {
      this.voteCounts = data;
// localStorage.setItem('voteCounts', JSON.stringify(this.voteCounts));
      console.log(this.voteCounts);
    });
  }

  getPercentage(optionId: number): number {

    const totalVotes = this.getTotalVotes();
    return totalVotes === 0
      ? 0
      : Math.round((this.voteCounts[optionId] / totalVotes) * 100);
  }

  getTotalVotes(): number {
    return Object.values(this.voteCounts).reduce(
      (total, count) => total + count,
      0
    );
  }

  changeStatus() {
    this.pollService.changeStatus().subscribe();
  }

  searchPollQuestion(): void {
    if (this.searchPoll.trim() !== "") {
      this.pollService
        .getPollByPollName(this.searchPoll)
        .subscribe((pollarray) => {
          this.pollarray = pollarray;
          this.searchPoll = "";
        });
    }
  }

  resetSearchPoll() {
    this.display = true;
    this.searchPoll = "";
  }

  deletePoll(id: any) {
    this.pollService.deletePoll(id).subscribe((data) => {});
  }

  updatePoll(id: any, data: any, value: any) {
    console.log(value);
    this.pl.pollId = id;
    this.pl.pollQuestion = this.pollQuestion;
    this.pl.options = value;
    console.log(this.pl.options);
    console.log(this.pl);
    this.pollService.updatePoll(this.pl).subscribe((data) => {});
  }

  toggleDisplayPolls() {
    this.display = !this.display;
  }

  reset() {
    this.view = true;
  }

  refresh() {
    location.reload();
  }

  getTotalVoteCount(){
   
    this.pollService.getAllVoteCount().subscribe((count)=>{
      this.votes = count;
      localStorage.setItem('votes', JSON.stringify(this.votes));

      console.log(this.votes)
  });
}
}

