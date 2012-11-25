package org.li.wallet.resources;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import static com.google.common.base.Preconditions.*;

public class JsonProcessor {
    private static String file = "expense.json";
    ObjectMapper mapper = new ObjectMapper();

    private void write(@Nonnull Expenses expenses) {
        checkNotNull(expenses);
        try {
            mapper.writeValue(new File(file), expenses);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @return expenses that record in the json file and if there are not
     *         records or the file doesn't exist, return null.
     */
    public Expenses read() {
        try {
            return mapper.readValue(new File(file), Expenses.class);
        } catch (JsonParseException e) {
            return null;
        } catch (JsonMappingException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * this method add a record of expense to the json file, if there is not the
     * file we will create one. and if in the file there are no records, this
     * record will be the first record
     * 
     * @param dto
     */
    public void addExpense(@Nonnull ExpenseDto dto) {
        checkNotNull(dto);
        Expenses expenses = read();
        if (expenses != null) {
            expenses.getExpenses().add(dto);
        } else {
            List<ExpenseDto> list = new ArrayList<ExpenseDto>();
            list.add(dto);
            expenses = new Expenses(list);
        }
        write(expenses);
    }
    
	public void editExpense(@Nonnull ExpenseDto dto, int index) {
		checkNotNull(dto);
		checkArgument(index >= 0);
		Expenses expenses = read();
		ExpenseDto editDto = expenses.getExpenses().get(index);
		editDto.setAmount(dto.getAmount());
		editDto.setAuthor(dto.getAuthor());
		editDto.setDate(dto.getDate());
		editDto.setReason(dto.getReason());

		write(expenses);
	}

    /**
     * this method delete a definite record in the json file
     * 
     * @param index
     */
    public void deleteExpense(@Nonnegative int index) {
        checkArgument(index >= 0);
        Expenses expenses = read();
        if (expenses != null) {
            expenses.getExpenses().remove(index);
            write(expenses);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
    	Calendar calendar = Calendar.getInstance();
		ExpenseDto dto = new ExpenseDto("liyongfeng", 50, "dish", new MyDate(
				calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH)));
        List<ExpenseDto> list = new ArrayList<ExpenseDto>();
        list.add(dto);

        Expenses expenses = new Expenses();
        expenses.setExpenses(list);

        JsonProcessor processor = new JsonProcessor();
         processor.write(expenses);
        // processor.addExpense(new ExpenseDto("hanxiao", 100, "dianfei",
        // Calendar.getInstance()));
        // processor.addExpense(dto);
        // processor.addExpense(dto);

        expenses = processor.read();
        if (expenses == null) {
            System.out.println("no file or no records");
            return;
        }

        System.out.println(expenses.getExpenses());
    }

}
