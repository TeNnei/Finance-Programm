CREATE FUNCTION trigger_s_before_lns () RETURNS trigger AS $First$
BEGIN

INSERT INTO summary_subtotal(code, today, saldo_in_som, saldo_in_usd, debit_som, debit_usd, saldo_out_som, saldo_out_usd) VALUES (
    NEW.debit, 	
    NEW.date_of,	
    COALESCE((SELECT saldo_out_som FROM summary_subtotal WHERE code = NEW.debit AND today < NEW.date_of ORDER BY today DESC LIMIT 1), 0),
	COALESCE((SELECT saldo_out_usd FROM summary_subtotal WHERE code = NEW.debit AND today < NEW.date_of ORDER BY today DESC LIMIT 1), 0),
    NEW.som,
	NEW.usd,
    0.00 + NEW.som,
    0.00 + NEW.usd
) ON CONFLICT (code, today) DO UPDATE SET 
    debit_som = summary_subtotal.debit_som + NEW.som,
	debit_usd = summary_subtotal.debit_usd + NEW.usd,
    saldo_out_som = summary_subtotal.saldo_out_som + NEW.som,
	saldo_out_usd = summary_subtotal.saldo_out_usd + NEW.usd;

INSERT INTO summary_subtotal(code, today, saldo_in_som, saldo_in_usd, credit_som, credit_usd, saldo_out_som, saldo_out_usd) VALUES (
    NEW.kredit, 	
    NEW.date_of,	
    COALESCE((SELECT saldo_out_som FROM summary_subtotal WHERE code = NEW.kredit AND today < NEW.date_of ORDER BY today DESC LIMIT 1), 0),
	COALESCE((SELECT saldo_out_usd FROM summary_subtotal WHERE code = NEW.kredit AND today < NEW.date_of ORDER BY today DESC LIMIT 1), 0),
    NEW.som,
	NEW.usd,
    0.00 - NEW.som,
    0.00 - NEW.usd
) ON CONFLICT (code, today) DO UPDATE SET 
    credit_som = summary_subtotal.credit_som + NEW.som,
	credit_usd = summary_subtotal.credit_usd + NEW.usd,
    saldo_out_som = summary_subtotal.saldo_out_som - NEW.som,
	saldo_out_usd = summary_subtotal.saldo_out_usd - NEW.usd;
RETURN NEW;
END;
$First$
LANGUAGE  plpgsql;

CREATE TRIGGER main_in
BEFORE INSERT ON maininf FOR EACH ROW
EXECUTE PROCEDURE trigger_s_before_lns();