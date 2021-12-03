package com.malkang.mitier.a105_minesweeper02;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    GridLayout gridLayout;
    int mineCount = 12;      // 지뢰의 숫자
    int gridSize = 10;       // 동적할당을 위해 존재, 하지만 사용하지 않는다.
    int[] minePosition;     // 지뢰의 위치
    ArrayList<Cell> cells;
    ArrayList<ImageButton> imageButtons;
    Cell[][] mineCells;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gridlayout);
        imageButtons = new ArrayList<ImageButton>();
        cells = new ArrayList<Cell>();

        //Log.d("@@@@@@@@@@@@@@", ""+gridLayout.getChildCount());

        //=======================================================
        // 해야할 것
        // 2. 주변 검색 하기
        // 3. 빈칸 자동으로 채우기
        // 4. 게임 상태 ( 게임중, 게임오버)
        // 5. 버튼 상태 ( 일반, 눌림(숫자), 지뢰, (깃발))

        //=======================================================
        // 완료한 것
        // 1. 지뢰 설치



        // 그림과 데이터 분리
        // 그림 : 버튼 숫자
        // 데이터 : 지뢰, 상태값

        // 지뢰 설치
        // 배열 또는 리스트에

        gameSetup(10, 12);
        mineSetup();

        checkNumber();

        drawImage();



        // Tag를 버튼의 index로 사용한다.
        for(int i = 0; i < gridLayout.getChildCount(); i++)
        {
            //imageButtons.get(i).setTag(i);
            //Log.d("@@@@@@@@@@@@", ""+ imageButtons.get(i).getTag());
            //Log.d("@@@@@@@@@@@@", "" + gridLayout.getChildAt(i).getTag());
            //Log.d("@@@@@@@@@@@@@@@", "" + gridLayout.getChildAt(i).getId());

            int index = i;
            gridLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("@@@@@@@@@@@@@@@@@", "" + index);
                    // 자신을 선택했음으로 변경하고
                    // 주변을 오픈? 폭탄른 오픈 하면 안됨.
                    cells.get(index).setChecked(true);
                    openAround(index);
                    drawImage();
                }
            });
        }

    }

    // 1열 방식
    public void gameSetup(int gridSize, int mineCount)
    {
        this.gridSize = gridSize;
        this.mineCount = mineCount;
        minePosition = new int[mineCount];

        for(int i = 0 ; i< gridSize * gridSize; i++)
        {
            cells.add(new Cell());
            cells.get(i).setIndex(i);
            cells.get(i).setCount(0);
            cells.get(i).setChecked(false);
            cells.get(i).setMine(false);
        }
    }

    // cols, rows 방식
    public void CellSetup(int cellSize)
    {
        // 게임판의 크기를 정한다. 우선 10 고정
        mineCells = new Cell[cellSize][cellSize];

        for(int i = 0 ; i< cellSize; i++)
        {
            for(int j = 0 ; j < cellSize; j++)
            {
                mineCells[i][j] = new Cell(i+j);
            }
        }
    }

    public void mineSetup()
    {
        for(int i = 0; i < gridLayout.getChildCount(); i++)
        {
            // 이미지 버튼의 ID 와 Tag를 설정한다.
            // 사실 Tag 만 설정해도 작동이 될 것이다.
            gridLayout.getChildAt(i).setTag(i);

            // id를 정렬시킨다.
            gridLayout.getChildAt(i).setId(i);

            // 리스트에 따로 넣어야 하는 것인가?
            // 그리드 레이아웃에 있는 자식을 찾아서 순서대로 List에 넣는다.
            // 처음 부터 ListView 로 만든다면?
            imageButtons.add((ImageButton)gridLayout.getChildAt(i));
            // gridLayout.getChildAt()
            // 으로 자식에 접근이 가능하다.
        }

        // 지뢰의 갯수 만큼 반복
        for(int i = 0 ; i < mineCount; i++)
        {
            int r = new Random().nextInt(gridSize * gridSize);
            minePosition[i] = r;

            // 중복되는 수 제거
            for(int j = 0; j < i; j++)
            {
                if(minePosition[i] == minePosition[j])
                {
                    i--;
                }
            }

            // Cells에 대입하기, 지뢰 대입
            cells.get(minePosition[i]).setMine(true);
        }
    }

    // 지뢰 생성과 함께 숫자를 생성 하는 방법
    // Cell을 선택하면 체크하는 방법
    // 선택된 것이 지뢰가 아니면 주변을 검색
    // 지뢰가 있으면 숫자를 증가
    // 숫자가 0 이면 다시 주변 검색, 0 이면 오픈
    //

    // 주변의 지뢰를 검색 하는거 테스트
    // 자신의 숫자를 증가 시키는 것
    public void checkNumber()
    {
        for(int i = 0; i < gridSize * gridSize; i++)
        {
            // 선택된 것의 위치에서 검색 해야 한다.
            // 만약 선택된것에서 -11, -10, -9, -1 , 1, 9 , 10, 11
            // 무엇을 선택 했는지 알아야 함.
            // 무한 반복 해야 하고 종료 조건이 필요
            // 0 ~ 99 까지 검색 한다. 한반만 지나가기 때문에 지가간것을 다시 검색해야 한다.

            // 주의!!!!!!
            // 1. 좌우 양 끝이 서로 인식 되는 문제가 있다.
            // 숫자 나열로 했을 경우의 문제......

            // 2. 빈칸(0)일 경우 주변을 다시 열어야 한다.

            // i 의 위치에서 검색
            if( (i -11) >= 0 && cells.get(i - 11).getMine() == true)
            {
                cells.get(i).addCount();
            }
            if( (i - 10) >= 0 &&  cells.get(i - 10).getMine() == true)
            {
                cells.get(i).addCount();
            }
            if( (i - 9) >= 0 && cells.get(i - 9).getMine() == true)
            {
                cells.get(i).addCount();
            }
            if( (i - 1) >= 0 &&  cells.get(i - 1).getMine() == true)
            {
                cells.get(i).addCount();
            }
            if( (i + 1 ) <= 99 &&  cells.get(i + 1).getMine() == true)
            {
                cells.get(i).addCount();
            }
            if( (i + 9 ) <= 99 &&  cells.get(i + 9).getMine() == true)
            {
                cells.get(i).addCount();
            }
            if( (i + 10 ) <= 99 &&  cells.get(i + 10).getMine() == true)
            {
                cells.get(i).addCount();
            }
            if(  (i + 11 ) <= 99 &&  cells.get(i + 11).getMine() == true)
            {
                cells.get(i).addCount();
            }
        }
    }

    public void checkNumber2()
    {
        // row, col 방식

    }

    public void openAround(int i)
    {
        if( (i -11) >= 0 &&  cells.get(i - 11).getMine() == false)
        {
            cells.get(i-11).setChecked(true);
        }
        if( (i - 10) >= 0 &&  cells.get(i - 10).getMine() == false)
        {
            cells.get(i-10).setChecked(true);
        }
        if( (i - 9) >= 0 && cells.get(i - 9).getMine() == false)
        {
            cells.get(i-9).setChecked(true);
        }
        if( (i - 1) >= 0 &&  cells.get(i - 1).getMine() == false)
        {
            cells.get(i-1).setChecked(true);
        }
        if( (i + 1 ) <= 99 &&  cells.get(i + 1).getMine() == false)
        {
            cells.get(i+1).setChecked(true);
        }
        if( (i + 9 ) <= 99 &&  cells.get(i + 9).getMine() == false)
        {
            cells.get(i+9).setChecked(true);
        }
        if( (i + 10 ) <= 99 &&  cells.get(i + 10).getMine() == false)
        {
            cells.get(i+10).setChecked(true);
        }
        if(  (i + 11 ) <= 99 &&  cells.get(i + 11).getMine() == false)
        {
            cells.get(i+11).setChecked(true);
        }
    }


    public void drawImage()
    {
        for(int i = 0; i < gridSize * gridSize; i++)
        {
            // imageButtons.get(i).setImageDrawable(null);
            // imageButtons.get(i).setImageResource((R.drawable.minesweeper_unopened_square));

            // 그림 표현도 8개 만들어야 한다.
            // 그림 표현을 줄일 수 있는 방법이 없는가?

            if(cells.get(i).getMine() == true && cells.get(i).getChecked() == true)
            {
                imageButtons.get(i).setImageResource(R.drawable.pit);
            }
            else if(cells.get(i).getCount() == 1 && cells.get(i).getChecked() == true)
            {
                imageButtons.get(i).setImageResource(R.drawable.minesweeper_1);
            }
            else if(cells.get(i).getCount() == 2 && cells.get(i).getChecked() == true)
            {
                imageButtons.get(i).setImageResource(R.drawable.minesweeper_2);
            }
            else if(cells.get(i).getCount() == 3 && cells.get(i).getChecked() == true)
            {
                imageButtons.get(i).setImageResource(R.drawable.minesweeper_3);
            }
            else if(cells.get(i).getCount() == 4 && cells.get(i).getChecked() == true)
            {
                imageButtons.get(i).setImageResource(R.drawable.minesweeper_4);
            }
            else if(cells.get(i).getCount() == 5 && cells.get(i).getChecked() == true)
            {
                imageButtons.get(i).setImageResource(R.drawable.minesweeper_5);
            }
            else if(cells.get(i).getCount() == 6 && cells.get(i).getChecked() == true)
            {
                imageButtons.get(i).setImageResource(R.drawable.minesweeper_6);
            }
            else if(cells.get(i).getCount() == 7 && cells.get(i).getChecked() == true)
            {
                imageButtons.get(i).setImageResource(R.drawable.minesweeper_7);
            }
            else if(cells.get(i).getCount() == 8 && cells.get(i).getChecked() == true)
            {
                imageButtons.get(i).setImageResource(R.drawable.minesweeper_8);
            }
            else if(cells.get(i).getCount() == 0 && cells.get(i).getChecked() == true)
            {
                imageButtons.get(i).setImageDrawable(null);
            }
            else
            {
                imageButtons.get(i).setImageResource((R.drawable.minesweeper_unopened_square));
            }


        }
    }
}

class Cell {
    private int index;
    private int count;          // 주변 지뢰의 수를 표현하기 위한 것
    private boolean isChecked;  // 선택되었는지 확인하기 위한 것
    private boolean isMine;     // 내가 지뢰인지 확인하기 위한 것

    Cell()
    {

    }

    Cell(int num)
    {
        index = num;
        count = 0;
        isChecked = false;
        isMine = false;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public int getIndex()
    {
        return index;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public void addCount() { this.count++; }

    public int getCount()
    {
        return count;
    }

    public void setChecked(boolean check)
    {
        isChecked = check;
    }

    public boolean getChecked()
    {
        return isChecked;
    }

    public void setMine(boolean mine)
    {
        isMine = mine;
    }

    public boolean getMine()
    {
        return isMine;
    }

}