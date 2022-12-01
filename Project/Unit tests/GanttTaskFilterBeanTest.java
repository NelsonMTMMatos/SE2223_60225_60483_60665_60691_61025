
package net.sourceforge.ganttproject.gui;

import biz.ganttproject.core.time.CalendarFactory;
import biz.ganttproject.core.time.GanttCalendar;
import net.sourceforge.ganttproject.GanttTask;
import net.sourceforge.ganttproject.task.TaskMutator;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GanttTaskFilterBeanTest {

    private static final int TASK_COUNT = 5;

    @Test
    void allTasksWithDuration2ShouldBePaintedRed() {
        List<GanttTask> tasks = new ArrayList<>(TASK_COUNT);
        for (int i = 0; i < TASK_COUNT; i++)
            tasks.add(new GanttTask(null, null, 0, null, i));

        Color filterColor = Color.RED;
        int insertedDuration = 2;
        for(GanttTask task : tasks){
            TaskMutator mutator = task.createMutator();
            if (insertedDuration == task.getDuration().getLength())
                mutator.setColor(filterColor);
            mutator.commit();

            assertEquals(filterColor, task.getColor());
        }
    }

    @Test
    void allTasksWithCompletion60ShouldBePaintedRed() {
        List<GanttTask> tasks = new ArrayList<>(TASK_COUNT);
        for (int i = 0; i < TASK_COUNT; i++)
            tasks.add(new GanttTask(null, null, 0, null, i));

        Color filterColor = Color.RED;
        int insertedCompletion = 60;
        for(GanttTask task : tasks){
            TaskMutator mutator = task.createMutator();
            if (insertedCompletion == task.getCompletionPercentage())
                mutator.setColor(filterColor);
            mutator.commit();

            assertEquals(filterColor, task.getColor());
        }
    }

    @Test
    void allTasksStartingInBeginningOfDecember2022ShouldBePaintedRed(){
        List<GanttTask> tasks = new ArrayList<>(TASK_COUNT);
        for (int i = 0; i < TASK_COUNT; i++)
            tasks.add(new GanttTask(null, null, 0, null, i));

        Color filterColor = Color.RED;
        GanttCalendar insertedBeginDate = CalendarFactory.createGanttCalendar(2022, 12, 1);
        for(GanttTask task : tasks){
            TaskMutator mutator = task.createMutator();
            if (insertedBeginDate.equals(task.getStart()))
                mutator.setColor(filterColor);
            mutator.commit();

            assertEquals(filterColor, task.getColor());
        }
    }

    @Test
    void allTasksEndingInBeginningOfDecember2022ShouldBePaintedRed(){
        List<GanttTask> tasks = new ArrayList<>(TASK_COUNT);
        for (int i = 0; i < TASK_COUNT; i++)
            tasks.add(new GanttTask(null, null, 0, null, i));

        Color filterColor = Color.RED;
        GanttCalendar insertedEndDate = CalendarFactory.createGanttCalendar(2022, 12, 1);
        for(GanttTask task : tasks){
            TaskMutator mutator = task.createMutator();
            if (insertedEndDate.equals(task.getEnd()))
                mutator.setColor(filterColor);
            mutator.commit();

            assertEquals(filterColor, task.getColor());
        }
    }

}
