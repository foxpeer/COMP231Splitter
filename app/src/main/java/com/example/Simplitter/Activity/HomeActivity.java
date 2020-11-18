package com.example.Simplitter.Activity;

import androidx.appcompat.app.AppCompatActivity;
import com.example.Simplitter.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    //  welcome message and user name
    TextView tvWelcome;
    Button btnCreateExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvWelcome = findViewById(R.id.textView_welcome); // Hey ~ display username textview

        btnCreateExpense = findViewById(R.id.button_createExpense);
        btnCreateExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId = 1;

                Intent createExpIntent = new Intent(getApplicationContext(), CreateExpenseActivity.class);
                createExpIntent.putExtra("userId", userId); //change the userId to database fetched userId
                startActivity(createExpIntent);
            }
        });
    }

    // click btn for create Expense page
    public void CreateExClick(View view) {

    }

    // click btn for view payment history page
    public void ViewPayClick(View view){

    }


    // this part for My Activities list view (used recycle viewer)
    // this is for the example what We should do it and I just added picture instead of
    // user activities . you should modify under code
    // related view => activity_home and row_item
       /* refer video :   https://www.youtube.com/watch?v=Ph3Ek6cLS4M

        //Assign Variable
        recyclerView = findViewById(R.id.recycler_view);

        //Create interger Array
        Integer [] activityLogo = {R.drawable.travel,R.drawable.food,R.drawable.home};

        //Create String array
        String[] activityName = {"Travel","Food","Shared House"};

        //Initialize arraylist
        mainModels = new ArrayList<>();
        for(int i =0 ; i < activityLogo.length; i++){
            MainModel model = new MainModel(activityLogo[i], activityName[i]);
            mainModels.add(model);
        }
        //design horizontal layout
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                HomeActivity.this,LinearLayoutManager.HORIZONTAL,false
        );
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //initialize mainadpater
        mainAdapter = new MainAdapter(HomeActivity.this,mainModels);
        //set mainadapter to recyclerview
        recyclerView.setAdapter(mainAdapter);

    }

    public class MainModel{
        Integer activityLogo;
        String  activityName;

        public MainModel(Integer activityLogo, String activityName) {
            this.activityLogo = activityLogo;
            this.activityName = activityName;
        }

        public Integer getActivityLogo() {
            return activityLogo;
        }
        public String getActivityName(){
            return activityName;
        }
    }
        public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHoler>{
            ArrayList<MainModel> mainModels;
            Context context;

            public  MainAdapter(Context context, ArrayList<MainModel> mainModels){
                this.context = context;
                this.mainModels = mainModels;
            }

            @NonNull
            @Override
            public MainAdapter.ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                // crate view
                View view = LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.row_item,parent,false);
                return new ViewHoler(view);
            }

            @Override
            public void onBindViewHolder(@NonNull MainAdapter.ViewHoler holder, int position) {
                //se logo to image view
                holder.imageView.setImageResource(mainModels.get(position).getActivityLogo());
                //set name to image view
                holder.textView.setText(mainModels.get(position).getActivityName());
            }

            @Override
           public int getItemCount(){
            return mainModels.size();
           }

            public class ViewHoler extends RecyclerView.ViewHolder{
                //Initialize variable
                ImageView imageView;
                TextView textView;

                public ViewHoler(@NonNull View itemView) {
                    super(itemView);
                    imageView = itemView.findViewById(R.id.image_View);
                    textView = itemView.findViewById(R.id.text_view);
                }
            }*/
}